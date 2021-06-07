# Future Improvemnets

## Database Connection
* The DB Connection is hard-coded in hibernate configuration file, which is a bad. They can be read from a properties file.
* Every API request creates a DB new connection. Connection pooling can be used.

## GET Temperature API
* The API currently support two values (day or hour) for "interval" param, which returns temperature data for 1 day or 1 hour respectively.
* Probably, this will not be a real world usecase. This solution be extended to use custm times like 1hour, 2hours, 2 day etc.

## Error Codes and Error Message
* Error codes and messages are defined in a Class (StatusCodes.java). These can be read from a properties file.

## Java Docs
* Though there are sufficient comments provided, the classes does not have enough information to generate an automatic JavaDoc. This can be improvised.