# MariaDB Install (Docker)
## 1. Docker Install - https://docs.docker.com/desktop/

## 2. MariaDB pull in docker
latest - <code>$ docker pull mariadb</code>

or 

version fix - <code>$ docker pull mariadb:10.4.25</code>

## 3. Check image download
<code>$ docker image ls</code>

## 4. Run MariaDB container
latest - <code>$ docker run --restart=unless-stopped --name mariadb -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=`my_password` mariadb</code>

or

version fix - <code>$ docker run --restart=unless-stopped --name mariadb -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=`my_password` mariadb:10.4.25</code>

## 5. Create volume and run MariaDB container with volume
* <code>$ docker volume create mariadb</code>
* run
  * latest - <code>$ docker run --restart=unless-stopped --name mariadb -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=`my_password` -v db-byclip:/var/lib/mysql mariadb</code>
  * version fix - <code>$ docker run --restart=unless-stopped --name mariadb -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=`my_password` -v db-byclip:/var/lib/mysql mariadb:10.4.25</code>

## 6. Check running container
<code>$ docker ps</code>

## 7. Enter MariaDB container
<code>$ docker exec -it mariadb /bin/bash</code>


