package student.contract;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDto {
    private String name;
    private Integer age;
    private String course;
    private String email;
}
