import { Inject, Injectable } from "@angular/core";
import { MAT_DATE_LOCALE, NativeDateAdapter } from "@angular/material/core";

/** Adapts the native JS Date for use with cdk-based components that work with dates. */
@Injectable()
export class CustomDateAdapter extends NativeDateAdapter {
    
    constructor(@Inject(MAT_DATE_LOCALE) matDateLocale: string) {
        super(matDateLocale);
    }
    
    getFirstDayOfWeek(): number {
     return 1;
    }
}