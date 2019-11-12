#!/bin/bash
str_today=`date +%Y%m%d`;
str_7day=`date -d "7 days ago" +%Y%m%d`;
rm -rf /home/mongobak/music;
mongodump -u wthxmusic -p wthxmusicfjyd -d music -o  /home/mongobak;
cd /home/mongobak;
tar -zcvf $str_today.tar.gz music;
rm -rf $str_7day.tar.gz;
