import { Component, EventEmitter, NgModule, Output } from '@angular/core';
import { LucideAngularModule, Menu  } from "lucide-angular";
import { GameProfileLevel } from "../../../features/game-profile/components/game-profile-level/game-profile-level";
import { LevelLayout } from "../level-layout/level-layout";

@Component({
  selector: 'app-header-layout',
  standalone: true,
  imports: [LucideAngularModule, LevelLayout],
  templateUrl: './header-layout.html',
  styleUrl: './header-layout.css',
})
export class HeaderLayout {
  readonly icons = { Menu }
  @Output() toggleMenu = new EventEmitter<void>()
}
