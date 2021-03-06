package hk.reco.music.iptv.common.ssh;

import java.io.InputStream;


/**
 * ssh文件传输工具
 * @author zhangsl
 * @date 2018年10月23日
 */
public class SshTransfer {
	
	/**
	 * 将文件传送到远端存储,不用判断路径问题,不存在的路径会自动创建
	 * @param server
	 * @param localFile 本地绝对路径
	 * @param remoteFile 远端绝对路径,注意linux下的文件分隔符为'/'
	 */
	public static void transfer(LinuxServer server, String localFile, String remoteFile) throws Exception{
		JschConnect ssh = null;
		try{
			ssh = new JschConnect(server);
			ssh.transfer(localFile, remoteFile);
			ssh.close();
		}finally{
			if(ssh!=null)
				ssh.close();
		}
	}
	
	public static void transfer(String host, String username, String password, String localFile, String remoteFile) throws Exception{
		JschConnect ssh = null;
		try{
			ssh = new JschConnect(host,username,password);
			ssh.transfer(localFile, remoteFile);
			ssh.close();
		}finally{
			if(ssh!=null)
				ssh.close();
		}
	}
	
	public static void transfer(String host, String username, String password, InputStream is, String remoteFile) throws Exception{
		transfer(host,username,password,22,is,remoteFile);
	}

	public static void transfer(String host, String username, String password, Integer port,InputStream is, String remoteFile) throws Exception{
		JschConnect ssh = null;
		try{
			ssh = new JschConnect(host,username,password,port);
			ssh.transfer(is, remoteFile);
			ssh.close();
		}finally{
			if(ssh!=null)
				ssh.close();
		}
	}
	
	public static void transfer(JschConnect ssh, String localFile, String remoteFile) throws Exception{
		ssh.transfer(localFile, remoteFile);
	}


	public static void downFile(LinuxServer server,  String remoteFile,String localFile) throws Exception{
		JschConnect ssh = null;
		try{
			ssh = new JschConnect(server);
			ssh.downFile(remoteFile,localFile );
			ssh.close();
		}finally{
			if(ssh!=null)
				ssh.close();
		}
	}
	
	public static void main(String[] args) throws Exception {
		LinuxServer server = new LinuxServer("183.251.62.117", "root", "wthx123456..");//福建移动文件服务器
		SshTransfer.transfer(server, "d:/00d96cbeaa5443c893d1d9828a9d2c53.jpg", "/data/test/fff.jpg");
	}
	

}
