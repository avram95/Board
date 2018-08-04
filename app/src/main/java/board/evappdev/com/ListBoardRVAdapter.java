package board.evappdev.com;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListBoardRVAdapter extends RecyclerView.Adapter<ListBoardRVAdapter.ListVH>{


public static class ListVH extends RecyclerView.ViewHolder {

    private TextView txtTitle;
    private TextView txtRegion;
    private TextView txtPrice;
    private TextView txtDescription;
    private ImageView imageBoard;

    ListVH(View itemView) {
        super(itemView);
        txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        txtRegion = (TextView) itemView.findViewById(R.id.txtRegion);
        txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
        txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
        imageBoard = (ImageView) itemView.findViewById(R.id.imageBoard);
    }
}

    private Context mContext;
    private List<Board> boards;

    public ListBoardRVAdapter(Context context) {
        this.mContext = context;
        this.boards = new ArrayList<>();
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
        notifyDataSetChanged();
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(String keyTopic);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @NonNull
    @Override
    public ListVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_list_item_board, viewGroup, false);
        return new ListBoardRVAdapter.ListVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBoardRVAdapter.ListVH listVH, final int position) {

        final Board board = boards.get(position);
        listVH.txtTitle.setText(board.getTitle());
        listVH.txtRegion.setText(board.getRegion());
        listVH.txtPrice.setText(String.valueOf(board.getPrice()) + "грн");
        listVH.txtDescription.setText(board.getDescription());
        if (!board.getPathImage().isEmpty()) {
            listVH.imageBoard.setImageBitmap(getImage(board.getPathImage()));
        }

        listVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.setOnItemClickListener(board.getTitle());

            }
        });
    }

    private Bitmap getImage(String savedPath) {
        File file = new File(savedPath);
        Bitmap mBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        return mBitmap;
    }
    @Override
    public int getItemCount() {
        return boards.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

}