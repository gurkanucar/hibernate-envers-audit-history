# Spring Boot Hibernate Envers Audit History Logging

#### USER_AUD

| ID | REV | REVTYPE | NAME           | USERNAME |
|----|-----|---------|----------------|----------|
| 1  | 1   | 0       | gurkan         | @grkn    |
| 2  | 2   | 0       | gurkan2        | @grkn2   |
| 1  | 3   | 1       | gurkan_UPDATE  | @grkn    |
| 1  | 4   | 1       | gurkan_UPDATE2 | @grkn    |
| 1  | 5   | 1       | gurkan_UPDATE  | @grkn    |
| 1  | 6   | 2       | null           | null     |

#### REVINFO

| REV | REVTSTMP        |
|-----|----------------|
| 1   | 1686001700932  |
| 2   | 1686001705279  |
| 3   | 1686001719637  |
| 4   | 1686001737580  |
| 5   | 1686001743540  |
| 6   | 1686001784099  |



### example responses ([postman collection here](https://github.com/gurkanucar/hibernate-envers-audit-history/blob/master/hibernate-envers-audit-history.postman_collection.json))

#### ```/user/revision/<ID>```

```json
[
    {
        "id": 1,
        "name": "gurkan",
        "username": "@grkn",
        "rev": 1,
        "revType": "ADD",
        "revDate": "2023-06-05T21:48:20.932+00:00"
    },
    {
        "id": 1,
        "name": "gurkan_UPDATE",
        "username": "@grkn",
        "rev": 3,
        "revType": "MODIFY",
        "revDate": "2023-06-05T21:48:39.637+00:00"
    },
    {
        "id": 1,
        "name": "gurkan_UPDATE2",
        "username": "@grkn",
        "rev": 4,
        "revType": "MODIFY",
        "revDate": "2023-06-05T21:48:57.580+00:00"
    },
    {
        "id": 1,
        "name": "gurkan_UPDATE",
        "username": "@grkn",
        "rev": 5,
        "revType": "MODIFY",
        "revDate": "2023-06-05T21:49:03.540+00:00"
    },
    {
        "id": 1,
        "name": null,
        "username": null,
        "rev": 6,
        "revType": "DELETE",
        "revDate": "2023-06-05T21:49:44.099+00:00"
    }
]
```



### res:
[https://progressivecoder.com/setting-hibernate-envers-spring-boot/](https://progressivecoder.com/setting-hibernate-envers-spring-boot/)
[http://www.java2s.com/example/java-api/org/hibernate/envers/auditreader/createquery-0-3.html](http://www.java2s.com/example/java-api/org/hibernate/envers/auditreader/createquery-0-3.html)
[https://thorben-janssen.com/hibernate-envers-query-data-audit-log/](https://thorben-janssen.com/hibernate-envers-query-data-audit-log/)
