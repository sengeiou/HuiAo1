package com.myp.huiao.api;

import com.myp.huiao.entity.BaseResult;
import com.myp.huiao.entity.ChioceBO;
import com.myp.huiao.entity.UserBO;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wuliang on 2017/3/9.
 * <p>
 * 此处存放后台服务器的所有接口数据
 */

public interface HttpService {

    String URL = "http://192.168.1.112:8080";   //测试服


    /**
     * 获取短信验证码
     */
    @FormUrlEncoded
    @POST("/huiao/api/appuser/verification")
    Observable<BaseResult<Object>> phoneVerification(@Field("mobile") String mobile,
                                                     @Field("verificationType") String verificationType);

    /**
     * 用户注册
     */
    @FormUrlEncoded
    @POST("/huiao/api/appuser/register")
    Observable<BaseResult<Object>> userRegister(@Field("account") String account,
                                                @Field("password") String password,
                                                @Field("verification") String verification);

    /**
     * 用户登录
     */
    @FormUrlEncoded
    @POST("/huiao/api/appuser/login")
    Observable<BaseResult<UserBO>> userLogin(@Field("account") String account, @Field("password") String password,
                                             @Field("uuid") String uuid);

    /**
     * 获取学历列表
     */
    @FormUrlEncoded
    @POST("/huiao/api/education/choice")
    Observable<BaseResult<List<ChioceBO>>> userLoadChoice(@Field("edu") String education);

    /**
     * 获取兴趣列表
     */
    @FormUrlEncoded
    @POST("/huiao/api/interest/choice")
    Observable<BaseResult<List<ChioceBO>>> userSetInterest(@Field("inter") String interest);

    /**
     * 修改用户数据
     */
    @FormUrlEncoded
    @POST("/huiao/api/appuser/update")
    Observable<BaseResult<UserBO>> userInterest(@Field("gender") String gender,
                                                @Field("educationId") String educationId,
                                                @Field("birthday") String birthday,
                                                @Field("interestIds") String interestIds);


}
