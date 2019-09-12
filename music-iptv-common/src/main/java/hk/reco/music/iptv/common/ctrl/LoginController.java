package hk.reco.music.iptv.common.ctrl;


import hk.reco.music.iptv.common.domain.IptvConsolePrivilege;
import hk.reco.music.iptv.common.domain.IptvConsoleUser;
import hk.reco.music.iptv.common.service.IptvConsolePrivilegeService;
import hk.reco.music.iptv.common.service.IptvConsoleUserService;
import hk.reco.music.iptv.common.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;

@Controller
public class LoginController {
	
	@Autowired
	private IptvConsoleUserService userService;
	@Autowired
	private IptvConsolePrivilegeService privilegeService;
    @Value("${iptv.console.button.control}")
	private Boolean consoleButtonShow;
	
	private static int width = 60;// 定义图片的width
	private static int height = 25;// 定义图片的height
	private static int codeCount = 4;// 定义图片上显示验证码的个数
	private static int xx = 10;
	private static int fontHeight = 18;
	private static int codeY = 14;
	private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9' };
	
	
	
	@RequestMapping("/")
	public String index(){
		return "login";
	}
	
	
	@RequestMapping("console/index")
	public String index(Model model,HttpServletRequest request){
		IptvConsoleUser user = (IptvConsoleUser)request.getSession().getAttribute(Constant.CONSOLE_USER_IN_SESSION);
		Integer roleId = user.getRoleId();
		List<IptvConsolePrivilege> privileges = privilegeService.findPrivilegeByRoleId(roleId);
		model.addAttribute("privileges", privileges);
		return "index";
	}
	
	
	
	@RequestMapping("login")
	@ResponseBody
	public Object login(HttpServletRequest request,HttpServletResponse response,String userName,String password,String valCode) throws Exception{
//		String valCodeInSession = (String)request.getSession().getAttribute(Constant.VALIDATECODE_IN_SESSION);
		//用户信息基本信息
//		if(!StringUtils.isNotBlank(valCode)||!StringUtils.isNotBlank(valCodeInSession)||!valCode.equalsIgnoreCase(valCodeInSession)){
//			return new JsonResult("验证码错误！！！");
//		}
		if(StringUtils.isBlank(userName)||StringUtils.isBlank(password)){
			return new JsonResult("用户名或密码为空！！！");
		}
		IptvConsoleUser user = userService.findUserByUserName(userName);
		if(user==null){
			return new JsonResult("用户不存在！！！");
		}
		if(!Md5Utils.encode(password).equals(user.getPassword())){
			return new JsonResult("密码错误！！！");
		}
		user.setButton(consoleButtonShow);
		//session处理
		request.getSession().setAttribute(Constant.CONSOLE_USER_IN_SESSION, user);
		//cookie处理用于session过期
		//request.getSession().setAttribute("consolebuttoncontrol",consoleButtonShow);
		Cookie cookie = new Cookie("token", URLEncoder.encode(SymmetricEncoder.AESEncode(JsonUtils.toJson(user)), "UTF-8"));
		cookie.setMaxAge(7200);
		cookie.setPath("/");
		response.addCookie(cookie);
		return new JsonResult(true,"登陆成功！！");
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Cookie []cookies=request.getCookies();
		for(Cookie cookie:cookies) {
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		return "login";
	}

	@RequestMapping("console/changePwd")
	@ResponseBody
	public Object changePwd(String oldPassword,String  newPassword,String confirmPassword,HttpServletRequest req) throws Exception{
			IptvConsoleUser user =  (IptvConsoleUser)req.getSession().getAttribute(Constant.CONSOLE_USER_IN_SESSION);
			if(!Md5Utils.encode(oldPassword).equals(user.getPassword())){
				return new JsonResult("原密码不正确");
			}
            if(!StringUtils.equals(newPassword,confirmPassword)){
              return new JsonResult("新密码和原密码不匹配");
		    }
		    newPassword = Md5Utils.encode(newPassword);
		    userService.updatePwdById(newPassword,user.getId());
			return new JsonResult();
	}

	@RequestMapping("test")
	public String test(){
		return "test";
	}
	
	@RequestMapping("verifyCode")
	public void verifyCode(HttpServletRequest request,HttpServletResponse resp){
		 // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Graphics2D gd = buffImg.createGraphics();
        // Graphics2D gd = (Graphics2D) buffImg.getGraphics();
        Graphics gd = buffImg.getGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);
        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
       // 设置字体。
        gd.setFont(font);
        // 画边框。
        gd.setColor(new Color(136, 136, 136));
        gd.drawRect(0, 0, width - 1, height - 1);
        // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(Color.BLACK);
 
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;
        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
           // 得到随机产生的验证码数字。
            String code = String.valueOf(codeSequence[random.nextInt(36)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i + 1) * xx, codeY);

            // 将产生的四个随机数组合在一起。
            randomCode.append(code);
        }
        //存放验证码
        request.getSession().setAttribute(Constant.VALIDATECODE_IN_SESSION, randomCode.toString());
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);
        resp.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            sos = resp.getOutputStream();
            ImageIO.write((RenderedImage)buffImg, "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}


	public static void main(String[] args) {
		String pwd = Md5Utils.encode("");
		System.out.println(pwd);
	}

}
