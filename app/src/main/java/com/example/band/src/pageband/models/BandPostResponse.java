package com.example.band.src.pageband.models;

import com.google.gson.annotations.SerializedName;

public class BandPostResponse {
//    {
//        "result": {
//        "postInfo": [
//        {
//            "postId": 69,
//                "userId": 1,
//                "userName": "구기성",
//                "userProfile": "prifile1.png",
//                "postCreatedAt": "2020년 09월08일 17:14",
//                "postContent": "밴드내용1",
//                "mediaUrl": "a.jpg",
//                "fileUrl": null,
//                "tagContent": null,
//                "numOfComment": 1,
//                "numOfView": 3,
//                "numOfExpression": 3,
//                "commentUserId": 1,
//                "commentUserName": "최지혁",
//                "commentCreatedAt": "2020년 09월08일 17:16",
//                "commentContent": "댓글내용1"
//        },
//        {
//            "postId": 70,
//                "userId": 2,
//                "userName": "안상희",
//                "userProfile": "prifile2.png",
//                "postCreatedAt": "2020년 09월08일 17:14",
//                "postContent": "밴드내용2",
//                "mediaUrl": null,
//                "fileUrl": "b.xml",
//                "tagContent": null,
//                "numOfComment": 1,
//                "numOfView": 1,
//                "numOfExpression": 1,
//                "commentUserId": 2,
//                "commentUserName": "김동혁",
//                "commentCreatedAt": "2020년 09월08일 17:16",
//                "commentContent": "댓글내용2"
//        },
//        {
//            "postId": 71,
//                "userId": 3,
//                "userName": "김성원",
//                "userProfile": "",
//                "postCreatedAt": "2020년 09월08일 17:14",
//                "postContent": "밴드내용3",
//                "mediaUrl": null,
//                "fileUrl": null,
//                "tagContent": "coffee",
//                "numOfComment": 1,
//                "numOfView": 1,
//                "numOfExpression": null,
//                "commentUserId": 3,
//                "commentUserName": "김윤석",
//                "commentCreatedAt": "2020년 09월08일 17:16",
//                "commentContent": "댓글내용3"
//        },
//        {
//            "postId": 72,
//                "userId": 4,
//                "userName": "구기천",
//                "userProfile": "prifile4.png",
//                "postCreatedAt": "2020년 09월08일 17:14",
//                "postContent": "밴드내용4",
//                "mediaUrl": null,
//                "fileUrl": null,
//                "tagContent": null,
//                "numOfComment": 1,
//                "numOfView": null,
//                "numOfExpression": 1,
//                "commentUserId": 4,
//                "commentUserName": "유지수",
//                "commentCreatedAt": "2020년 09월08일 17:16",
//                "commentContent": "댓글내용4"
//        },
//        {
//            "postId": 73,
//                "userId": 5,
//                "userName": "유민욱",
//                "userProfile": "",
//                "postCreatedAt": "2020년 09월08일 17:14",
//                "postContent": "밴드내용5",
//                "mediaUrl": null,
//                "fileUrl": null,
//                "tagContent": null,
//                "numOfComment": 1,
//                "numOfView": null,
//                "numOfExpression": 1,
//                "commentUserId": 5,
//                "commentUserName": "우지현",
//                "commentCreatedAt": "2020년 09월08일 17:16",
//                "commentContent": "댓글내용5"
//        },
//        {
//            "postId": 74,
//                "userId": 6,
//                "userName": "우지현",
//                "userProfile": "prifile6.png",
//                "postCreatedAt": "2020년 09월08일 17:14",
//                "postContent": "밴드내용6",
//                "mediaUrl": null,
//                "fileUrl": null,
//                "tagContent": null,
//                "numOfComment": 1,
//                "numOfView": null,
//                "numOfExpression": 4,
//                "commentUserId": 6,
//                "commentUserName": "유민욱",
//                "commentCreatedAt": "2020년 09월08일 17:16",
//                "commentContent": "댓글내용6"
//        },
//        {
//            "postId": 75,
//                "userId": 7,
//                "userName": "유지수",
//                "userProfile": "prifile7.png",
//                "postCreatedAt": "2020년 09월08일 17:14",
//                "postContent": "밴드내용7",
//                "mediaUrl": null,
//                "fileUrl": null,
//                "tagContent": null,
//                "numOfComment": 1,
//                "numOfView": null,
//                "numOfExpression": null,
//                "commentUserId": 7,
//                "commentUserName": "구기천",
//                "commentCreatedAt": "2020년 09월08일 17:16",
//                "commentContent": "댓글내용7"
//        },
//        {
//            "postId": 76,
//                "userId": 8,
//                "userName": "김윤석",
//                "userProfile": "prifile8.png",
//                "postCreatedAt": "2020년 09월08일 17:14",
//                "postContent": "밴드내용8",
//                "mediaUrl": null,
//                "fileUrl": null,
//                "tagContent": null,
//                "numOfComment": 3,
//                "numOfView": null,
//                "numOfExpression": null,
//                "commentUserId": 8,
//                "commentUserName": "구기성",
//                "commentCreatedAt": "2020년 09월08일 17:16",
//                "commentContent": "댓글내용10"
//        },
//        {
//            "postId": 77,
//                "userId": 9,
//                "userName": "김동혁",
//                "userProfile": "",
//                "postCreatedAt": "2020년 09월08일 17:14",
//                "postContent": "밴드내용9",
//                "mediaUrl": null,
//                "fileUrl": null,
//                "tagContent": null,
//                "numOfComment": null,
//                "numOfView": null,
//                "numOfExpression": 1,
//                "commentUserId": 9,
//                "commentUserName": null,
//                "commentCreatedAt": null,
//                "commentContent": null
//        },
//        {
//            "postId": 78,
//                "userId": 10,
//                "userName": "최지혁",
//                "userProfile": "prifile10.png",
//                "postCreatedAt": "2020년 09월08일 17:14",
//                "postContent": "밴드내용10",
//                "mediaUrl": null,
//                "fileUrl": null,
//                "tagContent": null,
//                "numOfComment": null,
//                "numOfView": null,
//                "numOfExpression": null,
//                "commentUserId": 10,
//                "commentUserName": null,
//                "commentCreatedAt": null,
//                "commentContent": null
//        }
//        ]
//    },
//        "isSuccess": true,
//            "code": 100,
//            "message": "밴드 게시물 조회 성공"
//    }

    // 서버에서 보내준 response를 변수명에 맞춰 자동으로 parsing 해준다
    @SerializedName("result")
    private BandPostResult result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public BandPostResponse(BandPostResult result, boolean isSuccess, int code, String message) {
        this.result = result;
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public BandPostResult getResult() {
        return result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
