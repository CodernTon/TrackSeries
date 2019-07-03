package com.example.antonlyngfelt.trackyourseries;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class AddSeriesTestAndroid {
    @Rule
    public ActivityTestRule<AddSeries> mAddSeries = new ActivityTestRule<>(AddSeries.class);

    @Test
    public void testAddSerieToDatabase()throws Exception{
        onView(withId(R.id.serieName))
                .perform(setTextInTextView("Avengers"));
        onView(withId(R.id.serieSeason))
                .perform(setTextInTextView("2"));
        onView(withId(R.id.serieEpisode))
                .perform(setTextInTextView("2"));

        onView(withId(R.id.addSerie)).perform(click());

        onView(withId(R.id.serieName)).check(matches(withText("")));
        onView(withId(R.id.serieSeason)).check(matches(withText("")));
        onView(withId(R.id.serieEpisode)).check(matches(withText("")));
    }

    @Test
    public void testRemoveSerieFromDatabase()throws Exception{
        onView(withId(R.id.serieName))
                .perform(setTextInTextView("Avengers"));

        onView(withId(R.id.removeSerie)).perform(click());

        onView(withId(R.id.serieName)).check(matches(withText("")));
        onView(withId(R.id.serieSeason)).check(matches(withText("")));
        onView(withId(R.id.serieEpisode)).check(matches(withText("")));
    }

    public static ViewAction setTextInTextView(final String value){
        return new ViewAction() {
            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TextView.class));
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((TextView) view).setText(value);
            }

            @Override
            public String getDescription() {
                return "replace text";
            }
        };
    }
}
