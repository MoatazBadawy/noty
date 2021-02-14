package com.gad.notekeeper;

import android.os.Parcel;
import android.os.Parcelable;

public final class NoteInfo implements Parcelable{
    private CourseInfo mCourse;
    private String mTitle;
    private String mText;

    public NoteInfo(CourseInfo course, String title, String text) {
        mCourse = course;
        mTitle = title;
        mText = text;
    }

    /* Parcelable Methods "to read" */
    // to read the values from write method "back-in" look at (Parcel in)
    private NoteInfo(Parcel in) {
        mCourse = in.readParcelable(CourseInfo.class.getClassLoader());
        mTitle = in.readString();
        mText = in.readString();
    }

    // here we will create our parcel values objects after read it form constrictor above
    public static final Creator<NoteInfo> CREATOR = new Creator<NoteInfo>() {
        @Override
        public NoteInfo createFromParcel(Parcel in) {
            return new NoteInfo(in);
        }

        // Create the appropriate size by number of values
        // so we pass to it an size as a parameter
        @Override
        public NoteInfo[] newArray(int size) {
            return new NoteInfo[size];
        }
    };
    /* End of "Parcelable Methods "to read" and create */

    public CourseInfo getCourse() {
        return mCourse;
    }

    public void setCourse(CourseInfo course) {
        mCourse = course;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    // will organize the display notes in the screen "to make our notes form display in the listView"
    private String getCompareKey() {
        return mCourse.getCourseId() + "|" + mTitle + "|" + mText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteInfo that = (NoteInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    // call getCompareKey method and make it toString to can display as string
    @Override
    public String toString() {
        return getCompareKey();
    }

    /* Parcelable Methods "to write" */
    // add additional information to parcelable variables "most case be 0 or hashcode"
    // because we will not add any thing
    @Override
    public int describeContents() {
        return 0;
    }

    // write the variables and make it parcel
    // to convert it from objects to bytes
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mCourse, 0);
        parcel.writeString(mTitle);
        parcel.writeString(mText);
    }
    /* End of "Parcelable Methods "to write" */
}
