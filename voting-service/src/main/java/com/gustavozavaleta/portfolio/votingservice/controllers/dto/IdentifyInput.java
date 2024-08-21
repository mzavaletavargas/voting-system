package com.gustavozavaleta.portfolio.votingservice.controllers.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class IdentifyInput implements Serializable {
     @NotNull
     @Digits(integer = 8, fraction = 0)
     private Integer documentId;
     @NotNull
     @Digits(integer = 1, fraction = 0)
     private Integer verificationNumber;

     public MultipartFile getVerificationImage() {
          return verificationImage;
     }

     public void setVerificationImage(MultipartFile verificationImage) {
          this.verificationImage = verificationImage;
     }

     private MultipartFile verificationImage;


     public @NotNull @Digits(integer = 8, fraction = 0) Integer getDocumentId() {
          return documentId;
     }

     public void setDocumentId(@NotNull @Digits(integer = 8, fraction = 0) Integer documentId) {
          this.documentId = documentId;
     }

     public @NotNull @Digits(integer = 1, fraction = 0) Integer getVerificationNumber() {
          return verificationNumber;
     }

     public void setVerificationNumber(@NotNull @Digits(integer = 1, fraction = 0) Integer verificationNumber) {
          this.verificationNumber = verificationNumber;
     }
}
