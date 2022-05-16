package com.example.lanlineelderdemo.web.form.menu;

import com.example.lanlineelderdemo.domain.menu.OpenType;
import com.sun.istack.NotNull;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;

@Data
public class MenuForm {
    @NotNull
    private String restaurantName;

    @NotNull
    private OpenType openType;

    @NotNull
    private String menuName;

    @NotNull
    private Integer numberOfMeal;

    @NotNull
    private Integer price;

    public MenuForm(Row row) {
        this.restaurantName = row.getCell(0).getStringCellValue();
        this.openType = OpenType.find(row.getCell(1).getStringCellValue());
        this.menuName = row.getCell(2).getStringCellValue();
        this.numberOfMeal = (int) row.getCell(3).getNumericCellValue();
        this.price = (int) row.getCell(4).getNumericCellValue();
    }
}
