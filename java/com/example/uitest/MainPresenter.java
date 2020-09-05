package com.example.uitest;

public class MainPresenter implements MainContract_interface.Presenter {

    private MainContract_interface.View MainView;
    private int CurrentNavigation = R.id.nav_news;

    public MainPresenter(MainContract_interface.View view){
        this.MainView = view;
        view.setPresenter(this);
    }


    public void switchNavigation(int fragment_id){
        this.CurrentNavigation = fragment_id;
        switch (fragment_id)
        {
            case R.id.nav_news:
                this.MainView.switchToNews();
                break;
            case R.id.nav_coviddata:
                this.MainView.switchToCovidData();
                break;
            case R.id.nav_settings:
                this.MainView.switchToSettings();
                break;
            case R.id.nav_about:
                this.MainView.switchToAbout();
                break;
            default:
                break;
        }
    }

    @Override
    public int getCurrentNavigationid() {
        return this.CurrentNavigation;
    }


    @Override
    public void subscribe() {
        switchNavigation(this.CurrentNavigation);
    }

    @Override
    public void unsubsribe() {

    }
}
