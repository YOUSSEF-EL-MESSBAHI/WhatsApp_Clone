package com.example.ChatAppWebSock.request;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RenameGroupRequest {
    private String groupName;
}
