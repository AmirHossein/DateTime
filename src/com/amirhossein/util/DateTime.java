package com.amirhossein.util;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Amir Hossein Hodjati Pour <me@amir-hossein.com>
 * @version 1.0
 * copyright
 *
 */
public class DateTime {
	public static final int YEAR = Calendar.YEAR;
	public static final int MONTH = Calendar.MONTH;
	public static final int DAY = Calendar.DATE;
	public static final int DAY_OF_YEAR = Calendar.DAY_OF_YEAR;
	public static final int HOUR = Calendar.HOUR;
	public static final int MINUTE = Calendar.MINUTE;
	public static final int SECOND = Calendar.SECOND;
	public static final int MILLISECOND = Calendar.MILLISECOND;

	public static enum ComparisonType {
		YEAR,
		MONTH,
		DAY,
		HOUR,
		MINUTE,
		SECOND,
		DATE,
		CLOCK,
		DATETIME
	}

	public static enum Format {
		DATE("yyyy-MM-dd"),
		TIME("hh:mm:ss"),
		DATETIME("yyyy-MM-dd hh:mm:ss"),
		READABLE_DATE("dd MMM yyyy"),
		READABLE_TIME("hh:mm"),
		READABLE_DATETIME("dd MMM yyyy hh:mm");

		private String format;

		Format(String format) {
			this.format = format;
		}

		public String toString() {
			return this.format;
		}
	}

	private Calendar calendar = Calendar.getInstance();

	public DateTime() {
	}

	public DateTime(Timestamp timestamp) {
		this.setTime(timestamp);
	}

	public DateTime(Date date) {
		this.setTime(date);
	}

	public DateTime(Calendar calendar) {
		this.setTime(calendar);
	}

	public DateTime(DateTime dateTime) {
		this.setTime(dateTime);
	}

	public DateTime(int year, int month, int day) {
		this.setTime(year, month, day);
	}

	public DateTime(int year, int month, int day, int hour, int minute, int second) {
		this.setTime(year, month, day, hour, minute, second);
	}

	public DateTime(String dateTime) {
		this.setTime(dateTime);
	}

	public DateTime(String date, String time) {
		this.setTime(date, time);
	}

	public DateTime(long milliseconds) {
		this.setTime(milliseconds);
	}


	public DateTime setTime(Timestamp timestamp) {
		this.calendar.setTimeInMillis(timestamp.getTime());
		return this;
	}

	public DateTime setTime(Date date) {
		this.calendar.setTime(date);
		return this;
	}

	public DateTime setTime(Calendar calendar) {
		this.calendar = (Calendar) calendar.clone();
		return this;
	}

	public DateTime setTime(DateTime dateTime) {
		this.calendar.setTimeInMillis(dateTime.getTime());
		return this;
	}

	public DateTime setTime(int year, int month, int day) {
		return this.setDate(year, month, day);
	}

	public DateTime setTime(int year, int month, int day, int hour, int minute, int second) {
		return this.setDate(year, month, day).setClock(hour, minute, second);
	}

	public DateTime setTime(String dateTime) {
		return this.parse(dateTime);
	}

	public DateTime setTime(String date, String time) {
		return this.parse(date, time);
	}

	public DateTime setTime(long milliseconds) {
		this.calendar.setTimeInMillis(milliseconds);
		return this;
	}

	public DateTime resetTime() {
		this.calendar = Calendar.getInstance();
		return this;
	}


	private DateTime set(int field, int value) {
		switch (field) {
			case YEAR:
			case DAY_OF_YEAR:
			case HOUR:
			case MINUTE:
			case SECOND:
			case MILLISECOND:
				this.calendar.set(field, value);
				break;
			case MONTH:
				this.calendar.set(MONTH, value - 1);
				break;
			case DAY:
				this.calendar.set(Calendar.DATE, value);
				break;
		}
		return this;
	}

	public DateTime setYear(int year) {
		return this.set(YEAR, year);
	}

	public DateTime setMonth(int month) {
		return this.set(MONTH, month);
	}

	public DateTime setDay(int day) {
		return this.set(DAY, day);
	}

	public DateTime setDayOfYear(int dayOfYear) {
		return this.set(DAY_OF_YEAR, dayOfYear);
	}

	public DateTime setHour(int hour) {
		return this.set(HOUR, hour);
	}

	public DateTime setMinute(int minute) {
		return this.set(MINUTE, minute);
	}

	public DateTime setSecond(int second) {
		return this.set(SECOND, second);
	}

	public DateTime setMillisecond(int millisecond) {
		return this.set(MILLISECOND, millisecond);
	}

	public DateTime setDate(int year, int month, int day) {
		return this.setTime(year, month, day, 0, 0, 0);
	}

	public DateTime setClock(int hour, int minute, int second) {
		return this.set(HOUR, hour).set(MINUTE, minute).set(SECOND, second);
	}


	private int get(int field) {
		switch (field) {
			case YEAR:
			case DAY_OF_YEAR:
			case HOUR:
			case MINUTE:
			case SECOND:
			case MILLISECOND:
				return this.calendar.get(field);
			case MONTH:
				return this.calendar.get(MONTH) + 1;
			case DAY:
				return this.calendar.get(Calendar.DATE);
		}
		return 0;
	}

	public int getYear() {
		return this.get(YEAR);
	}

	public int getMonth() {
		return this.get(MONTH);
	}

	public int getDay() {
		return this.get(DAY);
	}

	public int getDayOfYear() {
		return this.get(DAY_OF_YEAR);
	}

	public int getHour() {
		return this.get(HOUR);
	}

	public int getMinute() {
		return this.get(MINUTE);
	}

	public int getSecond() {
		return this.get(SECOND);
	}

	public int getMillisecond() {
		return this.get(MILLISECOND);
	}

	public long getTime() {
		return this.calendar.getTimeInMillis();
	}


	private DateTime add(int field, int value) {
		switch (field) {
			case YEAR:
			case HOUR:
			case MINUTE:
			case SECOND:
				this.calendar.add(field, value);
				break;
			case MONTH:
				this.calendar.add(MONTH, value);
				break;
			case DAY:
				this.calendar.add(Calendar.DATE, value);
				break;
		}
		return this;
	}

	public DateTime addYear(int year) {
		return this.add(YEAR, year);
	}

	public DateTime addMonth(int month) {
		return this.add(MONTH, month);
	}

	public DateTime addDay(int day) {
		return this.add(DAY, day);
	}

	public DateTime addHour(int hour) {
		return this.add(HOUR, hour);
	}

	public DateTime addMinute(int minute) {
		return this.add(MINUTE, minute);
	}

	public DateTime addSecond(int second) {
		return this.add(SECOND, second);
	}


	private DateTime parse(String dateTime) {
		try {
			return this.setTime(Timestamp.valueOf(dateTime));
		} catch (IllegalArgumentException e) {
			try {
				return this.setTime(java.sql.Date.valueOf(dateTime));
			} catch (IllegalArgumentException e1) {
				System.out.println("Error: Unsupported date format to parse.");
				return this.resetTime();
			}
		}
	}

	private DateTime parse(String date, String time) {
		return this.parse(date + " " + time);
	}


	public boolean after(Object obj) {
		return this.calendar.after(this.getCalendar(obj));
	}

	public boolean before(Object obj) {
		return this.calendar.before(this.getCalendar(obj));
	}

	public int compareTo(Object obj) {
		return this.calendar.compareTo(this.getCalendar(obj));
	}

	public boolean equals(Object obj, ComparisonType... comparisonTypes) {
		Calendar calendar = this.getCalendar(obj);
		if (calendar != null) {
			if (comparisonTypes == null || comparisonTypes.length == 0) {
				return this.calendar.equals(calendar);
			}
			for (ComparisonType comparisonType : comparisonTypes) {
				if (!this.equals(calendar, comparisonType)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	private boolean equals(Calendar calendar, ComparisonType comparisonType) {
		switch (comparisonType) {
			case YEAR:
				return this.calendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR);
			case MONTH:
				return this.calendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH);
			case DAY:
				return this.calendar.get(Calendar.DATE) == calendar.get(Calendar.DATE);
			case HOUR:
				return this.calendar.get(Calendar.HOUR) == calendar.get(Calendar.HOUR);
			case MINUTE:
				return this.calendar.get(Calendar.MINUTE) == calendar.get(Calendar.MINUTE);
			case SECOND:
				return this.calendar.get(Calendar.SECOND) == calendar.get(Calendar.SECOND);
			case DATE:
				return this.calendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) && this.calendar.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR);
			case CLOCK:
				return this.calendar.get(Calendar.HOUR) == calendar.get(Calendar.HOUR)
					&& this.calendar.get(Calendar.MINUTE) == calendar.get(Calendar.MINUTE)
					&& this.calendar.get(Calendar.SECOND) == calendar.get(Calendar.SECOND);
			case DATETIME:
				return this.calendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
					&& this.calendar.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)
					&& this.calendar.get(Calendar.HOUR) == calendar.get(Calendar.HOUR)
					&& this.calendar.get(Calendar.MINUTE) == calendar.get(Calendar.MINUTE)
					&& this.calendar.get(Calendar.SECOND) == calendar.get(Calendar.SECOND);
		}
		return false;
	}

	private Calendar getCalendar(Object obj) {
		if (obj instanceof Calendar) {
			return (Calendar) obj;
		}
		if (obj instanceof Timestamp) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(((Timestamp) obj).getTime());
			return calendar;
		}
		if (obj instanceof Date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime((Date) obj);
			return calendar;
		}
		if (obj instanceof DateTime) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(((DateTime) obj).getTime());
			return calendar;
		}
		return null;
	}


	public Calendar toCalendar() {
		return (Calendar) this.calendar.clone();
	}

	public Date toDate() {
		return new Date(this.calendar.getTimeInMillis());
	}

	public Timestamp toTimestamp() {
		return new Timestamp(this.calendar.getTimeInMillis());
	}

	public String toString() {
		return this.toString(Format.DATE);
	}

	public String toString(Format format) {
		return this.toString(format.toString());
	}

	public String toString(String format) {
		try {
			return new SimpleDateFormat(format).format(this.calendar.getTime());
		} catch (Exception e) {
			System.out.println("BONO Error: Unsupported date format or object.");
			return "";
		}
	}

	public DateTime clone() {
		return new DateTime(this.calendar);
	}
}
