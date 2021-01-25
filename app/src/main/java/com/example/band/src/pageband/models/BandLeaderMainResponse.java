package com.example.band.src.pageband.models;

import com.google.gson.annotations.SerializedName;

public class BandLeaderMainResponse {
//        {
//                "result": {
//                "bandId": 1,
//                        "bandName": "changeBandName",
//                        "isOpened": "N",
//                        "color": "#1EC900",
//                        "bandMemberNo": 11,
//                        "bandImg": "www.change.png",
//                        "bandIntroduction": "우리 밴드는 멋있다.",
//                        "createdAt": "2020년 09월",
//                        "restrictMemeberNo": 0,
//                        "registerQuestion": "내 나이는 몇살이게?",
//                        "registerGender": "NULL",
//                        "minAge": 20,
//                        "maxAge": 25,
//                        "isRegisterNoticed": "N",
//                        "isSecretAvailable": "N"
//        },
//                "isSuccess": true,
//                "code": 100,
//                "message": "밴드 리더 상세 정보 조회 성공"
//        }

        // 서버에서 보내준 response를 변수명에 맞춰 자동으로 parsing 해준다
        @SerializedName("result")
        private BandLeader result;

        @SerializedName("isSuccess")
        private boolean isSuccess;

        @SerializedName("code")
        private int code;

        @SerializedName("message")
        private String message;

        public BandLeaderMainResponse(BandLeader result, boolean isSuccess, int code, String message) {
                this.result = result;
                this.isSuccess = isSuccess;
                this.code = code;
                this.message = message;
        }

        public BandLeader getResult() {
                return result;
        }

        public void setResult(BandLeader result) {
                this.result = result;
        }

        public boolean isSuccess() {
                return isSuccess;
        }

        public void setSuccess(boolean success) {
                isSuccess = success;
        }

        public int getCode() {
                return code;
        }

        public void setCode(int code) {
                this.code = code;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }
}
