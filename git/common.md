1. 打开SS代理
2. 设置全局代理,需要查看自己的端口是不是也是1080
3. 享受吧

```shell
git config --global http.proxy http://127.0.0.1:1080
git config --global https.proxy https://127.0.0.1:1080
```

如果在输入这条命令之前，已经输入全局代理的话，可以输入进行取消

```shell
git config --global --unset http.proxy
git config --global --unset https.proxy
```

对于SSH协议无效

```shell
git clone git@github.com:xxxxxx/xxxxxx.git
```
