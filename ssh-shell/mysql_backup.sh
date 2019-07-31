#!/bin/bash
PASS='wthx6868!@#'
BACKUPDIR=/data/mysql_bak
DIR=`date -d "today" +%Y%m%d`

mysqldump   -p$PASS study > $BACKUPDIR/mysqlbak$DIR.sql
find /data/mysql_bak/*  -type d -ctime +5 | xargs rm -rf
