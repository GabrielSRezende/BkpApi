#!/bin/bash

realiza_backup(){
        for database in *.sql
        do
                mysql -u root -proot bugtracker < $database 2> /dev/null
                mv $database /var/lib/mysql/backups
        done
}

cd /var/lib/mysql/backup_gcp
if find /var/lib/mysql/backup_gcp -maxdepth 0 -empty | read v; 
then
        bash /root/telegram.sh "-788892618" "Docker BKP - Pasta com os arquivos de backup vazia!"
else
        realiza_backup
        if [ $? -eq 0 ]
        then
                bash /root/telegram.sh "-788892618" "Docker BKP - Backup do Mantis restaurado com sucesso!"
                echo "Restauracao completa!"
        else 
                bash /root/telegram.sh "-788892618" "Docker BKP - Problema na restauracao do backup do Mantis!"
                echo "Pasta vazia!"
        fi
fi

