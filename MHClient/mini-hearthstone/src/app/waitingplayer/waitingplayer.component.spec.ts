import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WaitingplayerComponent } from './waitingplayer.component';

describe('WaitingplayerComponent', () => {
  let component: WaitingplayerComponent;
  let fixture: ComponentFixture<WaitingplayerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WaitingplayerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WaitingplayerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
