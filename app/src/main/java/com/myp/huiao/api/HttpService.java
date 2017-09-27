package com.myp.huiao.api;

import com.myp.huiao.entity.BannerBO;
import com.myp.huiao.entity.BaseResult;
import com.myp.huiao.entity.ChioceBO;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.entity.CourserClassifyBO;
import com.myp.huiao.entity.EvaluateBO;
import com.myp.huiao.entity.TeachersBo;
import com.myp.huiao.entity.UserBO;
import com.myp.huiao.mvp.BaseRequestView;

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
//    String URL = "http://192.168.15.100:8080";


    /**
     * 获取短信验证码
     */
    @FormUrlEncoded
    @POST("/huiao/api/appuser/verification")
    Observable<BaseResult<Object>> phoneVerification(@Field("mobile") String mobile,
                                                     @Field("verificationType") String verificationType);

    /**
     * 验证身份
     */
    @FormUrlEncoded
    @POST("/huiao/api/appuser/verifuser")
    Observable<BaseResult<UserBO>> userAuthentication(@Field("account") String account,
                                                      @Field("mobile") String mobile,
                                                      @Field("verification") String verification);

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
     * 修改密码
     */
    @FormUrlEncoded
    @POST("/huiao/api/appuser/update")
    Observable<BaseResult<UserBO>> userPassWord(@Field("password") String password, @Field("pwd") String pwd);

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

    /**
     * 用于实现获取首页专栏、热门课程。
     */
    @FormUrlEncoded
    @POST("/huiao/api/sahcourse/list")
    Observable<BaseResult<List<CourserBO>>> hotCource(@Field("courseType") String courseType);

    /**
     * 获取轮播图
     */
    @FormUrlEncoded
    @POST("/huiao/api/banner/banners")
    Observable<BaseResult<List<BannerBO>>> bannersList(@Field("source") String source);

    /**
     * 获取课程分类
     */
    @FormUrlEncoded
    @POST("/huiao/api/classify/classifies")
    Observable<BaseResult<List<CourserClassifyBO>>> courserClassify(@Field("cla") String cla);

    /**
     * 获取课程列表
     *
     * @param price         价格排序 asc升序 desc降序
     * @param buyCount      热门、销量排序 asc升序 desc降序
     * @param time          添加时间 排序 asc升序 desc降序
     * @param facilityValue 难易度排序 asc升序 desc降序
     * @param courseTypeId  课程分类id搜索，可与排序参数同时使用
     * @param courseName    课程名称搜索，可与排序参数同时使用
     * @param apiPage       页数默认1
     * @param apiSize       条数默认10条/页
     */
    @FormUrlEncoded
    @POST("/huiao/api/course/list")
    Observable<BaseResult<List<CourserBO>>> courserList(@Field("coursePrice") String price,
                                                        @Field("courseBuyCount") String buyCount,
                                                        @Field("courseTime") String time,
                                                        @Field("courseFacilityValue") String facilityValue,
                                                        @Field("courseTypeId") String courseTypeId,
                                                        @Field("courseName") String courseName,
                                                        @Field("apiPage") String apiPage,
                                                        @Field("apiSize") String apiSize);

    /**
     * 获取课程详情
     */
    @FormUrlEncoded
    @POST("/huiao/api/course/detail")
    Observable<BaseResult<CourserBO>> courserMessage(@Field("courseId") String courseId);


    /**
     * 获取课程评价列表
     */
    @FormUrlEncoded
    @POST("/huiao/api/valuation/list")
    Observable<BaseResult<List<EvaluateBO>>> courserPingJia(@Field("courseId") String courseId);


    /**
     * 获取教师详情
     */
    @FormUrlEncoded
    @POST("/huiao/api/teacher/teachers")
    Observable<BaseResult<TeachersBo>> teacherMessage(@Field("teacherId") String teacherId);


    /**
     * 获取教师相关教程
     */
    @FormUrlEncoded
    @POST("/huiao/api/course/teacher/list")
    Observable<BaseResult<List<CourserBO>>> teacherCoursers(@Field("teacherId") String teacherId);


}
