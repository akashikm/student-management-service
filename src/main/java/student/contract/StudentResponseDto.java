package student.contract;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {
    private UUID id;
    private Integer studentCode;
    private String name;
    private Integer age;
    private String course;
    private String email;
}
