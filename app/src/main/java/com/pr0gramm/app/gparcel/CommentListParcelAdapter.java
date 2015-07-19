package com.pr0gramm.app.gparcel;

import android.os.Parcel;

import com.google.common.reflect.TypeToken;
import com.pr0gramm.app.api.pr0gramm.response.Comment;
import com.pr0gramm.app.gparcel.core.ParcelAdapter;

import java.util.List;

/**
 */
public class CommentListParcelAdapter extends ParcelAdapter<List<Comment>> {
    private static final TypeToken<List<Comment>> token = new TypeToken<List<Comment>>() {
    };

    public CommentListParcelAdapter(List<Comment> values) {
        super(token, values);
    }

    protected CommentListParcelAdapter(Parcel parcel) {
        super(token, parcel);
    }

    public static final Creator<CommentListParcelAdapter> CREATOR = new Creator<CommentListParcelAdapter>() {
        @Override
        public CommentListParcelAdapter createFromParcel(Parcel source) {
            return new CommentListParcelAdapter(source);
        }

        @Override
        public CommentListParcelAdapter[] newArray(int size) {
            return new CommentListParcelAdapter[size];
        }
    };
}
