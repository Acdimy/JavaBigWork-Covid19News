package com.example.uitest;

import Base.Presenter_interface;
import Base.View_interface;


public interface MainContract_interface {

    interface View extends View_interface<Presenter>
    {

        void switchToNews();

        void switchToCovidData();

        void switchToSettings();

        void switchToAbout();

    }


    interface Presenter extends Presenter_interface
    {

        void switchNavigation(int fragment_id);

        int getCurrentNavigationid();

    }


}
