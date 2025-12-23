# Spring Boot の学習メモ

## Repository（リポジトリ層）

- **役割**: DBアクセス
- **補足**: Spring Data JPA を使うと、リポジトリインターフェースで `JpaRepository` を継承するだけで CRUD が自動で使える  

```java
// < UsersがEntityに対応 Longが主キーの型>
public interface UsersRepository extends JpaRepository<Users, Long> { }
```

&emsp;&nbsp;**JpaRepository が提供するメソッド これらは自動生成してくれる**

1. findAll() ⇀ `SELECT * FROM USERS`
1. findById(id) ⇀ `SELECT * FROM USERS WHERE id = ?`
1. save(entity) ⇀ `INSERT` または `UPDATE`
1. delete(entity) ⇀ `DELETE`




