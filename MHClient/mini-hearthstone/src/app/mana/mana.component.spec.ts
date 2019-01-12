import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ManaComponent} from './mana.component';

describe('ManaComponent', () => {
    let component: ManaComponent;
    let fixture: ComponentFixture<ManaComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ManaComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ManaComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
