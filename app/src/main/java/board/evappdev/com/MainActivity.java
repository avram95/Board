package board.evappdev.com;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private BoardSQLiteHandler boardSQLiteHandler;
    private List<Board> boardList;
    private ListBoardRVAdapter listTopicRVAdapter;
    private ListView listViewFilter;
    private SimpleBaseAdapter sortBaseAdapter;
    private RelativeLayout rlFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        listViewFilter = (ListView) findViewById(R.id.listViewFilter);
        rlFilter = (RelativeLayout) findViewById(R.id.rlFilter);
        rlFilter.setVisibility(View.GONE);
        setSupportActionBar(toolbar);

        boardSQLiteHandler = new BoardSQLiteHandler(getApplicationContext());
        boardSQLiteHandler.deleteBoardData();
        if (boardSQLiteHandler.getBoardData() == null || boardSQLiteHandler.getBoardData().size() < 10)
        addDataToDataBase();
        boardList = boardSQLiteHandler.getBoardData();
        RecyclerView rvBoards = (RecyclerView) findViewById(R.id.rvBoards);
        rvBoards.setLayoutManager(new LinearLayoutManager(this));
        listTopicRVAdapter = new ListBoardRVAdapter(getApplicationContext());
        rvBoards.setAdapter(listTopicRVAdapter);
        listTopicRVAdapter.setBoards(boardList);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, AddBoardActivity.class));
            }
        });
        final FloatingActionButton fabFilter = (FloatingActionButton) findViewById(R.id.fabFilter);
        fabFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rlFilter.setVisibility(View.VISIBLE);
                fab.setVisibility(View.GONE);
                fabFilter.setVisibility(View.GONE);
            }
        });

        listTopicRVAdapter.setOnItemClickListener(new ListBoardRVAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(String keyWordsList) {

            }
        });
        filter();
        listViewFilter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<Board> boards = new ArrayList<>();
                for (Board board : boardList) {
                    if (sortBaseAdapter.getListStrings().get(position).equals(board.getRegion())) {
                        boards.add(board);
                    }
                }
                listTopicRVAdapter.setBoards(boards);
                rlFilter.setVisibility(View.GONE);
                fab.setVisibility(View.VISIBLE);
                fabFilter.setVisibility(View.VISIBLE);
            }
        });
    }

    private void addDataToDataBase() {
        for (int i = 0; i < 4; i++) {
            Board board = new Board(
                    "Сдам 1-к/к - Соляные",
                    "Центральный",
                    2600,
                    "Мебель,стиральная машина автомат,холодильник, телевизор,интернет.",
                    "");
            Board board2 = new Board(
                    "Сдам 1-к/к - Соляные",
                    "Заводской",
                    2600,
                    "Мебель,стиральная машина автомат,холодильник, телевизор,интернет.",
                    "");
            boardSQLiteHandler.addBoard(board);
            boardSQLiteHandler.addBoard(board2);
        }
    }

    private void filter() {
        Set<String> regions = new LinkedHashSet<>();

        for (Board board : boardList) {
            regions.add(board.getRegion());
        }
        List<String> regionList = new ArrayList<>(regions);
        sortBaseAdapter = new SimpleBaseAdapter(this);
        listViewFilter.setAdapter(sortBaseAdapter);
        sortBaseAdapter.setList(regionList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (boardSQLiteHandler.getBoardData() != null)
        listTopicRVAdapter.setBoards(boardSQLiteHandler.getBoardData());

    }
}
