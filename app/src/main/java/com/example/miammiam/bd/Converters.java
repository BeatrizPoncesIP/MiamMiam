package com.example.miammiam.bd;

import androidx.room.TypeConverter;
import java.sql.Date;

public class Converters {

    // Converte a data (Date) para Long (timestamp)
    @TypeConverter
    public static Long fromDate(Date date) {
        return (date == null) ? null : date.getTime();
    }

    // Converte o timestamp (Long) de volta para Date
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return (timestamp == null) ? null : new Date(timestamp);
    }
}
