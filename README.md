# User接口

1、接口：/user/{userId}

接口发送请求参数：userId:用户的账号

接口方法：exitUser

接口描述：是否存在用户

接口返回：数据状态码

2、接口：/user/register

接口发送请求参数：User实体类

接口方法：userRegistration（用户注册）

接口返回：UserInfo实体类

3、接口：/user/login

接口发送请求参数：userId，userPassword

接口方法：userLogin（用户登录）

接口返回：userId对应的UserInfo实体类

4、接口：/user/send_sms

接口发送请求参数：newPhone，HttpServletRequest

接口方法：sendSMS（发送短信验证码用于用户更换手机号）

接口返回：榛子云短信API数据格式

接口描述：用户发送新的手机号，服务器响应后，运用榛子云短信平台向用户所属手机号发送短信，将短信验证码存入session，设置1分钟时长。

5、接口：/user/receive_sms

接口发送请求参数：userId

接口方法：receiveSMS（接收短信）

接口返回：数据状态码

接口描述：用户发送验证码到服务器，服务器响应后查看session域中的验证码是否存在过期，若无过期则对比验证码，若正确则返回成功的数据，若失败则返回失败的数据。

6、接口：/user/check_phone/{userId}

接口发送请求参数：userId

接口方法：checkUserPhone（查询用户所在手机号是否存在）

接口返回：数据状态码

7、接口：/user/update_pwd

接口发送请求参数：userId，oldPassword，newPassword

接口方式：updatePassword（）

接口描述：用户修改密码

接口返回：数据状态码

# UserInfo接口

1、接口：/userInfo/{userId}

接口发送请求参数：userId:用户的账号

接口方法：findUserInfo

接口描述：通过userId查询用户个人信息

接口返回：UserInfo实体类（用户的个人信息）

2、接口：/userInfo/upload

接口发送请求参数：avatar(头像上传文件)，avatarName(文件名)

接口方法：userAvatarUpload

接口描述：用户可以上传头像，通过七牛云API将图片上传到七牛云平台，将图片挂载到平台上，转换为url链接。

接口返回：七牛云的图片链接

3、接口：/userInfo/update

接口发送请求参数：UserInfo实体类

接口方法：updateUserInfo

接口描述：前端发送实体类后，用户信息更新。

接口返回：数据状态码

# OrderForm接口

1、接口：/order/select/{userId}

接口发送参数：userId（用户账号）

接口方法：selectOrderForm（返回用户id所属的订单）

接口返回：OrderForm实体类

2、接口：/order/history/{userId}

接口发送参数：userId（用户账号）

接口方法：selectHistoryForm（返回用户id所属的历史订单）

接口返回：OrderForm实体类

3、接口：/order/getProducts/{productsId}

接口发送参数：productsId（产品Id）

接口方法：getProducts（返回产品所属的属性）

接口返回：Products实体类

4、接口：/order/insert

接口发送参数：PlaceOrderObject实体类属性（

```java
private String orderSender;
private String orderSenderPhone;
private String orderRecipient;
private String orderRecipientPhone;
private String orderFormStartAddress;
private String orderFormEndAddress;
private String orderFormUserId;
private String reserveTime;
private String orderFormProductsId;
private Double orderFormMoney;
```

）

接口方法：placeOrder（处理用户的下单请求）

接口返回：数据验证码

5、接口：/order/cancel/{number}

接口发送参数：number（订单号）

接口方法：cancelReserve（用户取消预约）

接口返回：数据状态码

6、接口：/order/delivery/{orderFormNumber}

接口发送参数：orderFormNumber（订单号）

接口方法：deliveryInfo（获取订单信息）

接口返回：订单列表

7、接口：/confirm/{orderFormNumber}

接口发送参数：orderFormNumber（订单号）

接口方法：confirmDelivery（确认收货处理）

接口返回：数据状态码