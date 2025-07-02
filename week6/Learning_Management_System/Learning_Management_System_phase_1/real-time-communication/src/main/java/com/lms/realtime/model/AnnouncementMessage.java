package com.lms.realtime.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementMessage {
    private String instructor;
    private String content;
    private LocalDateTime timestamp;
} 