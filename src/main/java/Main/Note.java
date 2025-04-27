package Main;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Note implements Serializable {
    private String content;
    private LocalDateTime createdAt;

    // Constructor
    public Note(String content) {
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Note{" +
                "content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
