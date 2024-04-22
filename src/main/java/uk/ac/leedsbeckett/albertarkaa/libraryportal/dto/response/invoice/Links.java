package uk.ac.leedsbeckett.albertarkaa.libraryportal.dto.response.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Links {
    private Link cancel;
    private Link pay;
}