# Redis docker 启动命令
    1 
    docker run -p 6379:6379 --restart=always --privileged -v $PWD/data:/data  -d redis:3.2 redis-server --appendonly yes
      命令说明：
      --restart=always 自动重启
      --privileged root权限
      -p 6379:6379 :将容器的6379端口映射到主机的6379端口
      -v $PWD/data:/data :将主机中当前目录下的data挂载到容器的/data
      redis-server --appendonly yes :在容器执行redis-server启动命令，并打开redis持久化配置
~~~~
    2 
    docker run --name redis -p 6379:6379 --restart=always -v $PWD/data:/data  -d redis:5.0.5 redis-server --appendonly yes daemonize yes
      参数说明：
      #本地运行
      -d
      #本地端口:Docker端口
      6379:6379
      #指定驱动盘
      -v
      #Redis的持久化文件存储
      $PWD/data
      #docker的镜像名
      redis
      #redis服务器
      redis-server
      #开启持久化
      --appendonly yes
      #这个运行的镜像的名称
      --name
      #守护进程
      daemonize yes
      #Docker启动容器就启动
      --restart=always
~~~~
      3 指定额外端口启动 
       docker run -p 6379:6380 --restart=always --privileged -v $PWD/data:/data  -d redis:3.2 redis-server   
   ~~~~  
      4  主从启动
docker run -it --name redis-1  --restart=always --privileged -v /home/lm/redis.conf:/usr/local/etc/redis/redis.conf -d -p 6379:6379 redis /bin/bash
~~~~
      5 哨兵启动
     docker run -it --name sentinel --restart=always --privileged -p 26379:26379 -v $PWD/sentinel.conf:/usr/local/etc/redis/sentinel.conf -d redis /bin/bash
