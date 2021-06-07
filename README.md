# Temperature Service

## Sample Requests

### Save Temparature
 
```
POST http://{HOSTNAME}/temperature/rest/service

[
    {
        "temperatureValue": "20",
        "time": "2021-06-06 16:40:00"
    },
    {
        "temperatureValue": "22",
        "time": "2021-06-06 16:48:00"
    }
]

Header
Content-Type: application/json
Accept: application/json
```

### Retrieve Temparature Values

```

GET http://{HOSTNAME}/temperature/rest/service?interval=day

Header
Content-Type: application/json
Accept: application/json

```

```
Supported Values for interval : hour | day
```