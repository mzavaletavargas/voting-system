package com.gustavozavaleta.portfolio.votingservice.controllers.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
public class IdentifyInput implements Serializable {
     @NotNull
     @Digits(integer = 8, fraction = 0)
     private Integer documentId;
     @NotNull
     @Digits(integer = 1, fraction = 0)
     private Integer verificationNumber;
     private MultipartFile verificationImage;
}
