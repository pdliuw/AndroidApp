package com.air.ocr;

import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.sdk.model.Word;

/**
 * @author pd_liu 2018/8/23
 * <p>
 * ReceiveIDCardResult
 * </p>
 * <p>
 * Guide:
 * 1、...
 * </p>
 */
public class ReceiveIDCardResult {


    public static class Success {

        private int direction;
        private int wordsResultNumber;
        private Word address;
        private Word idNumber;
        private Word birthday;
        private Word name;
        private Word gender;
        private Word ethnic;
        private String idCardSide;
        private String riskType;
        private String imageStatus;
        private Word signDate;
        private Word expiryDate;
        private Word issueAuthority;

        public Success(IDCardResult result) {

            direction = result.getDirection();
            wordsResultNumber = result.getWordsResultNumber();
            address = result.getAddress();
            idNumber = result.getIdNumber();
            birthday = result.getBirthday();
            name = result.getName();
            gender = result.getGender();
            ethnic = result.getEthnic();
            idCardSide = result.getIdCardSide();
            riskType = result.getRiskType();
            imageStatus = result.getImageStatus();
            signDate = result.getSignDate();
            expiryDate = result.getExpiryDate();
            issueAuthority = result.getIssueAuthority();

        }

        public int getDirection() {
            return this.direction;
        }

        public int getWordsResultNumber() {
            return this.wordsResultNumber;
        }

        public String getAddress() {
            return this.address.getWords();
        }

        public String getIdNumber() {
            return this.idNumber.getWords();
        }

        public String getBirthday() {
            return this.birthday.getWords();
        }

        public String getName() {
            return this.name.getWords();
        }

        public String getGender() {
            return this.gender.getWords();
        }

        public String getEthnic() {
            return this.ethnic.getWords();
        }

        public String getIdCardSide() {
            return this.idCardSide;
        }

        public String getRiskType() {
            return this.riskType;
        }

        public String getImageStatus() {
            return this.imageStatus;
        }

        public String getSignDate() {
            return this.signDate.getWords();
        }

        public String getExpiryDate() {
            return this.expiryDate.getWords();
        }

        public String getIssueAuthority() {
            return this.issueAuthority.getWords();
        }

        @Override
        public String toString() {
            return "Success{" +
                    "direction=" + direction +
                    ", wordsResultNumber=" + wordsResultNumber +
                    ", address=" + address +
                    ", idNumber=" + idNumber +
                    ", birthday=" + birthday +
                    ", name=" + name +
                    ", gender=" + gender +
                    ", ethnic=" + ethnic +
                    ", idCardSide='" + idCardSide + '\'' +
                    ", riskType='" + riskType + '\'' +
                    ", imageStatus='" + imageStatus + '\'' +
                    ", signDate=" + signDate +
                    ", expiryDate=" + expiryDate +
                    ", issueAuthority=" + issueAuthority +
                    '}';
        }
    }

    public static class Error {

        protected int errorCode;
        protected long logId;
        protected String errorMessage;
        protected Throwable cause;

        public Error(OCRError error) {
            this.errorCode = error.getErrorCode();
            this.logId = error.getLogId();
            this.errorMessage = error.getMessage();
            this.cause = error.getCause();
        }

        @Override
        public String toString() {
            return "Error{" +
                    "errorCode=" + errorCode +
                    ", logId=" + logId +
                    ", errorMessage='" + errorMessage + '\'' +
                    ", cause=" + cause +
                    '}';
        }
    }
}
