#!/bin/bash

#Função de backup
realiza_backup(){
        #Pegando a data atual e adicionando ao nome do arquivo
        hoje=$(date +%F)
        nome_arquivo=backup-$hoje.sql

        #Realizando backup
        mysqldump -u bn_mantis -pf958481e4c bitnami_mantis > /home/flavio/backups/$nome_arquivo
}

#Chamada da função
realiza_backup

#Verificando se ocorreu algum erro, caso seja diferente de 0, algum erro foi encontrado
if [ $? -eq 0 ]
then
        #Enviando mensagem para o telegram
        bash /root/telegram.sh "-788892618" "GCP - Backup realizado com sucesso!"
        echo "Backup realizado com sucesso"
else 
        #Enviando mensagem para o telegram
        bash /root/telegram.sh "-788892618" "GCP - Erro ao tentar realizar backup!"
        echo "Erro ao tentar realizar backup"
fi