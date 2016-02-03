echo "=========================="
echo "=         WARNING        ="
echo "=         ROOTING        ="
echo "=          GLASS         ="
echo "=========================="
adb reboot bootloader  # enter fastboot mode, takes 10-15 seconds
fastboot devices  # verify device is in fastboot, should see its serial #
fastboot oem unlock  # unlocking erases all data, you are prompted to run this command twice to be sure
fastboot flash boot boot.img  # gain root shell access with the rooted bootloader
fastboot reboot  # reboot the device
adb root  # start adb as root
adb shell  # access the shell as root
