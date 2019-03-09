## Introduce 
A simple mall server for wechat mini program mall 
## Prepare
### create database
```sql
create database mall
```
### create user 
```sql
 create user 'mall'@'localhost' identified by 'mall';
```
### grunt author 
```sql
grant all on mall.* to 'mall'@'localhost';
```
