package com.example.sslab.samplegroupapplication.common;

import com.example.sslab.samplegroupapplication.MainActivity;
import com.example.sslab.samplegroupapplication.samples.DaggerSample;

import javax.inject.Singleton;

import dagger.Component;

/**
 *
 * The injector class used in Dagger 2 is called a component.
 * It assigns references in our activities, services, or fragments to have access to singletons we earlier defined.
 * We will need to annotate this class with a @Component declaration. Note that the activities, services,
 * or fragments that can be added should be declared in this class with individual inject() methods:
 *
 * Injector class는 Dagger2에서 불려지는 컴포넌트로써 사용된다.
 * 그것은 우리의 activities,services,or fragment에서
 */

@Singleton
@Component ( modules = {NetworkApiModule.class, NetComponent.class})
public interface NetComponent {
    // to update field in your activities
    void inject(DaggerSample daggerSampleActivity);
}
