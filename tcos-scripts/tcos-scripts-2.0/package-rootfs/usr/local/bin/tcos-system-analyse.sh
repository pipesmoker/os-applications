#! /usr/bin/sudo /bin/bash

[ "$1" != "-p" ] && page="/bin/false"

IFS=$'\n'
for command in $(cat <<EOF
lspci -k
lsusb -v
fdisk -l /dev/sda /dev/sdb /dev/sdc /dev/sdd 
cat /proc/cpuinfo 
cat /proc/meminfo 
free -m
dmidecode  
ifconfig -a 
cat /proc/cmdline
uname -a
cat /etc/issue
xrandr -q
cat /var/log/Xorg.0.log
hwinfo
lshw
EOF
); do
    echo -e "\n########################################################"
    $page && (echo -e "... press any key for command: \"$command\"\n"; read bla)
    echo "... running command $command"
    eval "$command"
done

hwinfo


