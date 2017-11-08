package com.myp.huiao.api;

import com.myp.huiao.entity.ArticleBO;
import com.myp.huiao.entity.BannerBO;
import com.myp.huiao.entity.ChioceBO;
import com.myp.huiao.entity.CourserBO;
import com.myp.huiao.entity.CourserClassifyBO;
import com.myp.huiao.entity.EvaluateBO;
import com.myp.huiao.entity.MyCourserBO;
import com.myp.huiao.entity.PinLunBO;
import com.myp.huiao.entity.TeachersBo;
import com.myp.huiao.entity.TopicBO;
import com.myp.huiao.entity.TopicClaissIfyBO;
import com.myp.huiao.entity.UserBO;
import com.myp.huiao.util.rx.RxResultHelper;

import java.util.List;

import rx.Observable;

/**
 * Created by wuliang on 2017/4/19.
 * <p>
 * 所有网络请求方法
 */

public class HttpServiceIml {

    private static HttpService service;
    private static final String apiSize = "20";   //分页查询的所有接口默认每页20条

    /**
     * 获取代理对象
     */
    private static HttpService getService() {
        if (service == null)
            service = ApiManager.getInstance().configRetrofit(HttpService.class, HttpService.URL);
        return service;
    }


    /**
     * 获取验证码
     */
    public static Observable<Object> phoneVerification(String phone, String versionType) {
        return getService().phoneVerification(phone, versionType).compose(RxResultHelper.httpRusult());
    }

    /**
     * 验证身份
     */
    public static Observable<UserBO> userAuthentication(String account, String phone, String version) {
        return getService().userAuthentication(account, phone, version).compose(RxResultHelper.<UserBO>httpRusult());
    }


    /**
     * 修改密码
     */
    public static Observable<UserBO> userPassWord(String password, String pwd) {
        return getService().userPassWord(password, pwd).compose(RxResultHelper.<UserBO>httpRusult());
    }


    /**
     * 用户注册
     */
    public static Observable<Object> userRegister(String phone, String password, String version) {
        return getService().userRegister(phone, password, version).compose(RxResultHelper.httpRusult());
    }


    /**
     * 用户登录
     */
    public static Observable<UserBO> userLogin(String phone, String password, String uuid) {
        return getService().userLogin(phone, password, uuid).compose(RxResultHelper.<UserBO>httpRusult());
    }

    /**
     * 获取学历列表
     */
    public static Observable<List<ChioceBO>> userLoadChoice(String education) {
        return getService().userLoadChoice(education).compose(RxResultHelper.<List<ChioceBO>>httpRusult());
    }

    /**
     * 获取兴趣列表
     */
    public static Observable<List<ChioceBO>> userSetInterest(String interest) {
        return getService().userSetInterest(interest).compose(RxResultHelper.<List<ChioceBO>>httpRusult());
    }

    /**
     * 修改用户资料
     */
    public static Observable<UserBO> userInterest(String gender, String educationId, String birthday, String interestIds) {
        return getService().userInterest(gender, educationId, birthday, interestIds).compose(RxResultHelper.<UserBO>httpRusult());
    }

    /**
     * 用于实现获取首页专栏、热门课程。
     */
    public static Observable<List<CourserBO>> hotCourse(String courseType) {
        return getService().hotCource(courseType).compose(RxResultHelper.<List<CourserBO>>httpRusult());
    }

    /**
     * 获取轮播图
     */
    public static Observable<List<BannerBO>> bannersList(String source) {
        return getService().bannersList(source).compose(RxResultHelper.<List<BannerBO>>httpRusult());
    }

    /**
     * 获取课程分类
     */
    public static Observable<List<CourserClassifyBO>> cousersClassify(String classify) {
        return getService().courserClassify(classify).compose(RxResultHelper.<List<CourserClassifyBO>>httpRusult());
    }

    /**
     * 获取课程列表
     */
    public static Observable<List<CourserBO>> courserList(String price, String buyCount, String time,
                                                          String facilityValue, String courseTypeId,
                                                          String courseName, String apiPage) {
        return getService().courserList(price, buyCount, time, facilityValue, courseTypeId,
                courseName, apiPage, apiSize).compose(RxResultHelper.<List<CourserBO>>httpRusult());
    }

    /**
     * 获取课程详情
     */
    public static Observable<CourserBO> courserMessage(String courseId) {
        return getService().courserMessage(courseId).compose(RxResultHelper.<CourserBO>httpRusult());
    }


    /**
     * 获取课程评价列表
     */
    public static Observable<List<EvaluateBO>> courserPingjia(String courseId) {
        return getService().courserPingJia(courseId).compose(RxResultHelper.<List<EvaluateBO>>httpRusult());
    }

    /**
     * 获取教师详情
     */
    public static Observable<TeachersBo> teacherMessage(String teacherId) {
        return getService().teacherMessage(teacherId).compose(RxResultHelper.<TeachersBo>httpRusult());
    }

    /**
     * 获取教师相关教程
     */
    public static Observable<List<CourserBO>> teacherCoursers(String teacherId) {
        return getService().teacherCoursers(teacherId).compose(RxResultHelper.<List<CourserBO>>httpRusult());
    }


    /**
     * 获取用户课程
     */
    public static Observable<List<MyCourserBO>> courserMyList(String courseStatus, String pageNO) {
        return getService().courserMyList(courseStatus, pageNO, apiSize).compose(RxResultHelper.<List<MyCourserBO>>httpRusult());
    }

    /**
     * 获取今日新知
     */
    public static Observable<List<ArticleBO>> todayArticle(String articleType, String articlePage) {
        return getService().todayArticle(articleType, articlePage, apiSize).compose(RxResultHelper.<List<ArticleBO>>httpRusult());
    }


    /**
     * 收藏课程
     */
    public static Observable<Object> collectCourser(String courseId, String collectStatus) {
        return getService().collectCourser(courseId, collectStatus).compose(RxResultHelper.httpRusult());
    }

    /**
     * 精选推荐(发现页面)
     */
    public static Observable<List<TopicBO>> discoverFeatured() {
        return getService().discoverFeatured("1", null, "1", "4").compose(RxResultHelper.<List<TopicBO>>httpRusult());
    }

    /**
     * 分类列表
     */
    public static Observable<List<TopicClaissIfyBO>> classifyList(String apiPage) {
        return getService().classifyList(apiPage, apiSize).compose(RxResultHelper.<List<TopicClaissIfyBO>>httpRusult());
    }

    /**
     * 获取所有用户发布的热门话题
     */
    public static Observable<List<TopicBO>> HotTopicList(String topicStatus, String pageNo) {
        return getService().hotPicList(topicStatus, pageNo, apiSize).compose(RxResultHelper.<List<TopicBO>>httpRusult());
    }


    /**
     * 获取类别详情
     */
    public static Observable<TopicClaissIfyBO> classifyMessage(String categoryId) {
        return getService().classifyMessage(categoryId).compose(RxResultHelper.<TopicClaissIfyBO>httpRusult());
    }

    /**
     * 获取类别详情下的话题列表
     */
    public static Observable<List<TopicBO>> classifyTopicList(String topicStatus, String topicCategoryId,
                                                              String page) {
        return getService().discoverFeatured(topicStatus, topicCategoryId, page, apiSize).compose(RxResultHelper.<List<TopicBO>>httpRusult());
    }

    /**
     * 获取话题详情
     */
    public static Observable<TopicBO> topicMessage(String topicId) {
        return getService().toppicMessage(topicId).compose(RxResultHelper.<TopicBO>httpRusult());
    }

    /**
     * 获取话题一级评论
     */
    public static Observable<List<PinLunBO>> topicMessagePinLun(int id, String page) {
        return getService().topicMessagePinLun(id, page, apiSize).compose(RxResultHelper.<List<PinLunBO>>httpRusult());
    }


    /**
     * 获取二级评论
     */
    public static Observable<List<PinLunBO>> topicPinLunToPinLun(int parentId, String page) {
        return getService().topicPinLunToPinLun(parentId, page, apiSize).compose(RxResultHelper.<List<PinLunBO>>httpRusult());
    }


    /**
     * 发布话题评论
     */
    public static Observable<String> topicAddPinLun(String topicId, String content) {
        return getService().topicAddPinLun(topicId, content).compose(RxResultHelper.<String>httpRusult());
    }

    /**
     * 发布评论的评论
     */
    public static Observable<String> topicPinLunToPinLun(String discussId, String content) {
        return getService().pinLunToPinLun(discussId, content).compose(RxResultHelper.<String>httpRusult());
    }


    /**
     * 为话题点赞
     * <p>
     * status 点赞状态(0未点赞,1已点)
     */
    public static Observable<String> topicDianZan(String topicId, String status) {
        return getService().topicDianZan(topicId, status).compose(RxResultHelper.<String>httpRusult());
    }

    /**
     * 为评论点赞
     * <p>
     * status 点赞状态(0未点赞,1已点)
     */
    public static Observable<String> PinLunDianZan(String discussId, String status) {
        return getService().PinLunDianZan(discussId, status).compose(RxResultHelper.<String>httpRusult());
    }


    /**
     * 关注用户或取消关注用户
     */
    public static Observable<String> followUser(String followedUserId, String followStatus) {
        return getService().followUser(followedUserId, followStatus).compose(RxResultHelper.<String>httpRusult());
    }
}
