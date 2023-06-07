package algonquin.cst2335.torunse;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Tests the main activity of the app by entering an invalid password and checking the error message.
     */
    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatEditText = onView(withId(R.id.myEditText));

        appCompatEditText.perform(replaceText("12345"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.loginBtn));
        materialButton.perform(click());

        ViewInteraction textView = onView( withId(R.id.textView) );
        textView.check(matches(withText("You shall not pass!")));

    }

    /**
     * Tests the app by entering a password that is missing a digit and checking the error message.
     */
    @Test
    public void testFindMissingDigit(){
//find the view
        ViewInteraction appCompatEditText = onView( withId(R.id.myEditText));
//type in password123#$*
        appCompatEditText.perform(replaceText ( "Password*"));
//find the button
        ViewInteraction materialButton = onView(withId(R.id.loginBtn) );
//click the button
        materialButton.perform(click());
//find the text view
        ViewInteraction textView = onView( withId(R.id.textView) );
//check the text
        textView.check (matches (withText ("You shall not pass!")));
    }

    /**
     * Tests the app by entering a password that is missing an upper case letter and checking the error message.
     */
    @Test
    public void testFindMissingUpperCase(){
//find the view
        ViewInteraction appCompatEditText = onView( withId(R.id.myEditText));
//type in password123#$*
        appCompatEditText.perform(replaceText ( "assword123*"));
//find the button
        ViewInteraction materialButton = onView(withId(R.id.loginBtn) );
//click the button
        materialButton.perform(click());
//find the text view
        ViewInteraction textView = onView( withId(R.id.textView) );
//check the text
        textView.check (matches (withText ("You shall not pass!")));
    }

    /**
     * Tests the app by entering a password that is missing a lower case letter and checking the error message.
     */
    @Test
    public void testFindMissingLowerCase(){
//find the view
        ViewInteraction appCompatEditText = onView( withId(R.id.myEditText));
//type in password123#$*
        appCompatEditText.perform(replaceText ( "P123*"));
//find the button
        ViewInteraction materialButton = onView(withId(R.id.loginBtn) );
//click the button
        materialButton.perform(click());
//find the text view
        ViewInteraction textView = onView( withId(R.id.textView) );
//check the text
        textView.check (matches (withText ("You shall not pass!")));
    }

    /**
     * Tests the app by entering a password that is missing a special case letter and checking the error message.
     */
    @Test
    public void testFindMissingSpecial(){
//find the view
        ViewInteraction appCompatEditText = onView( withId(R.id.myEditText));
//type in password123#$*
        appCompatEditText.perform(replaceText ( "Password123"));
//find the button
        ViewInteraction materialButton = onView(withId(R.id.loginBtn) );
//click the button
        materialButton.perform(click());
//find the text view
        ViewInteraction textView = onView( withId(R.id.textView) );
//check the text
        textView.check (matches (withText ("You shall not pass!")));
    }

    /**
     * Tests the app by entering a password that has no errors and checking the success message.
     */
    @Test
    public void testFindNoErrors(){
//find the view
        ViewInteraction appCompatEditText = onView( withId(R.id.myEditText));
//type in password123#$*
        appCompatEditText.perform(replaceText ( "Password123*"));
//find the button
        ViewInteraction materialButton = onView(withId(R.id.loginBtn) );
//click the button
        materialButton.perform(click());
//find the text view
        ViewInteraction textView = onView( withId(R.id.textView) );
//check the text
        textView.check (matches (withText ("Your password meets the requirements")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
