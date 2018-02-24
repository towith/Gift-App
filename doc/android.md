# sign apk
keytool -genkey -v -keystore my.keystore -keyalg RSA -keysize 2048 -validity 10000 -alias gift
keytool -importkeystore -srckeystore my.keystore -destkeystore my.keystore -deststoretype pkcs12