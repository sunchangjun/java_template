str_month=`date -d "last month" +%Y-%m`;
cd /opt/tomcat1/logs;
tar -zcvf catalina.$str_month.tar.gz  catalina.$str_month*.out 
mv catalina.$str_month.tar.gz /opt/tomcat1/logs/backlog/;
rm -rf catalina.$str_month*.out;

cd /opt/tomcat2/logs;
tar -zcvf catalina.$str_month.tar.gz  catalina.$str_month*.out
mv catalina.$str_month.tar.gz /opt/tomcat2/logs/backlog/;
rm -rf catalina.$str_month*.out;
