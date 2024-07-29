package org.autosiso;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Holiday {

  public List<LocalDate> getListHoliday(){
    List<LocalDate> listHoliday = new ArrayList<LocalDate>();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    //public holidays for Comakeit
    listHoliday.add(LocalDate.parse("2023/01/26", dtf));
    listHoliday.add(LocalDate.parse("2023/03/07", dtf));
    listHoliday.add(LocalDate.parse("2023/03/22", dtf));
    listHoliday.add(LocalDate.parse("2023/08/15", dtf));
    listHoliday.add(LocalDate.parse("2023/09/18", dtf));
    listHoliday.add(LocalDate.parse("2023/10/02", dtf));
    listHoliday.add(LocalDate.parse("2023/10/24", dtf));
    listHoliday.add(LocalDate.parse("2023/12/25", dtf));
    return listHoliday;
  }


}
