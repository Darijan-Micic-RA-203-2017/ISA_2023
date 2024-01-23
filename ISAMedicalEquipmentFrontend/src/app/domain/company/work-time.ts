export class WorkTime {
    id: number;
    onMondaysThroughFridays: string;
    onSaturdays: string | null;
    onSundays: string | null;
    
    constructor(id: number, onMondaysThroughFridays: string, onSaturdays: string | null, onSundays: string | null) {
		this.id = id;
		this.onMondaysThroughFridays = onMondaysThroughFridays;
		this.onSaturdays = onSaturdays;
		this.onSundays = onSundays;
	}
}
