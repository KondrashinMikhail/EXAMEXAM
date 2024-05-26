document
    .getElementById('roomTimetableForm')
    .addEventListener('submit', async (e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        const response = await fetch("https://vm.nathoro.ru/timetable/by-room/" + encodeURIComponent(formData.get('room')));
        const data = await response.json();
        const wrapper = document.getElementById('timetableWrapper');
        data.forEach(el => renderWeek(el, wrapper))
    });

var renderWeek = (data, wrapper) => {
    const tbl = document.createElement("table");
    tbl.innerHTML = `
    <tr>
        <th>1</th>
        <th>2</th>
        <th>3</th>
        <th>4</th>
        <th>5</th>
        <th>6</th>
        <th>7</th>
        <th>8</th>
    </tr>`;
    data.days.forEach(day => {
        const week = document.createElement('tr');
        day.lessons.forEach(lesson => {
            week.innerHTML += lesson === null 
                ? `<td class="slot-empty">-</td>` 
                : `<td class="slot-full">${lesson.group.name}</td>`
        });
        tbl.append(week);
    });
    wrapper.append(tbl);
}
