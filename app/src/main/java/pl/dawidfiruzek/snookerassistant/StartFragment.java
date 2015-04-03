package pl.dawidfiruzek.snookerassistant;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment implements View.OnClickListener {

    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_start, container, false);

        Button buttonQuickMatch = (Button) rootView.findViewById(R.id.button_quick_match);
        buttonQuickMatch.setOnClickListener(this);

        Button buttonChampionship = (Button) rootView.findViewById(R.id.button_championship);
        buttonChampionship.setOnClickListener(this);

        Button buttonPlayers = (Button) rootView.findViewById(R.id.button_players);
        buttonPlayers.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_quick_match:
                onClickButtonQuickMatch();
                break;
            case R.id.button_championship:
            case R.id.button_players:
                break;
            default:
                Log.e(StartActivity.TAG, "Unexpected onClick item ID");
                break;
        }
    }

    public void onClickButtonQuickMatch(){
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
                .replace(R.id.container, new AddPlayersFragment())
                .addToBackStack(null)
                .commit();


//        Intent intent = new Intent(getActivity(), StartActivity.class);
//        intent.putExtra("GAME_MODE", "QUICK_MATCH");
//        startActivity(intent);

    }
}
