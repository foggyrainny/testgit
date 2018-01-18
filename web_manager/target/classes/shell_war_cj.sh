source /etc/profile
BUILD_ID=DONTKILLME
ps -ef|grep tomcat8082|awk '{print $2}'|xargs kill -9
cd #{path}/webapps/
mv #{name}.war /date/Backup/#{project}.war.bak
rm -rf #{name}
wget #{url}
cd ../
./bin/startup.sh
