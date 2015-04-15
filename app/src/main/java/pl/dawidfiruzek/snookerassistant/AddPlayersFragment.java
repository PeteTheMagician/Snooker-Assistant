package pl.dawidfiruzek.snookerassistant;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddPlayersFragment extends Fragment implements View.OnClickListener {


    public AddPlayersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_players, container, false);

        Button buttonNext = (Button) rootView.findViewById(R.id.addPlayer_button_next);
        buttonNext.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.addPlayer_button_next:
                onClickButtonNext();
                break;
            default:
                break;
        }
    }

    private void onClickButtonNext(){
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_fade_in, R.anim.abc_fade_out)
                .replace(R.id.container, new FrameChooserFragment())
                .addToBackStack(null)
                .commit();
    }
}
