package at.technikum_wien.miljevic.newsreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String LIST_KEY = "listKey";

    private NewsReaderAdapter newsReaderAdapter;
    private RecyclerView newsListView;
    private List<NewsModel> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsList = new ArrayList<>();

        newsListView = findViewById(R.id.rv_news_reader);
        // assign layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        newsListView.setLayoutManager(layoutManager);
        newsListView.setHasFixedSize(false);

        if (savedInstanceState != null && savedInstanceState.containsKey(LIST_KEY)) {
            newsList = savedInstanceState.getParcelableArrayList(LIST_KEY);
        } else {
            addDummyNewsData();
        }

        newsReaderAdapter = new NewsReaderAdapter(newsList);
        newsListView.setAdapter(newsReaderAdapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelableArrayList(LIST_KEY, (ArrayList<? extends Parcelable>) newsList);
    }

    // To be removed later once we use real data
    private void addDummyNewsData() {
        // COPY PASTED DATA FROM https://www.theguardian.com
        newsList.add(new NewsModel("'Guess what? We'll do it again': Sweden footballers vow to keep taking a knee",
                "The former Swedish ice hockey goalkeeper Sofia Reideborn was one of those who were critical of the players, saying the action would make people stop watching women’s football and that the players were “ruining it for future generations”.\n" +
                        "\n" +
                        "Eriksson, however, was unrepentant in her response, releasing a statement saying: “To take a knee was never close to being a hard decision for us. This was always the right thing to do. We know what we stand for and believe in. Yesterday we took a knee to show that we are against racism and discrimination in any way.",
                "The Guardian",
                new Date(),
                List.of("news", "Race", "Women's Football")));
        newsList.add(new NewsModel("Things aren’t going well for Odell Beckham Jr in Cleveland ",
                "The Browns were steamrolled in Week 1 by the Ravens, with Beckham, once the top headline grabber in the NFL’s most important market, relegated to the role of spectator.\n" +
                        "\n" +
                        "It’s not that long ago that Beckham felt like he was redefining the laws of physics and what was possible at the position. He was the feature star of the Giants bail-out offense during the back-end of the Eli Manning era: Manning would throw it and hope; Beckham would bail him out. ",
                "The Guardian",
                new Date(),
                List.of("features", "NFL", "US sports")));
        newsList.add(new NewsModel("Jemma Reekie savours Diamond League 800m win over housemate Laura Muir",
                "Even in a year without the Tokyo Olympics it has been a golden summer for Jemma Reekie, and the 22-year-old Scot again emphasised her striking talent by beating her friend and housemate Laura Muir in a tactical 800m at the Rome Diamond League.\n" +
                        "How Lamine Diack's 16-year reign in charge of IAAF led to a jail term\n" +
                        "Read more\n" +
                        "\n" +
                        "Reekie had won five out of six races over two laps in 2020, but given the strength of the field this was always going to be her stiffest test even before a slow and bumpy first lap. Yet Reekie was always prominent, and when Muir struck for home with 200m to go she sat on her shoulder before powering away to win in 1.59.76.",
                "The Guardian",
                new Date(),
                List.of("news")));
        newsList.add(new NewsModel("German football team socially distance from opponents, lose 37-0 ",
                "German amateur side SG Ripdorf/Molzen II sacrificed a tight defence for social distancing as they fielded only seven players as a coronavirus precaution in a 37-0 loss to local rivals SV Holdenstedt II.\n" +
                        "\n" +
                        "The preparations for Sunday’s match in Lower Saxony’s 3 Kreisklasse – the 11th tier of German football – were complicated when it emerged that Holdenstedt players had been in contact with an opponent infected with Covid-19 in a previous match.",
                "The Guardian",
                new Date(),
                List.of("news", "Coronavirus outbreak")));
        newsList.add(new NewsModel("Chess: Kasparov and Carlsen undone by internet glitches following 55-move draw",
                "Following their evocative 55-move draw last week in their first official game since 2004, both Garry Kasparov and Magnus Carlsen suffered separate internet glitches in a reminder that the booming pursuit of online chess has its own peculiar hazards to which even the greatest legends are not immune.\n" +
                        "\n" +
                        "Kasparov met Fabiano Caruana in round four of the $150,000 Champions Showdown aware that he had been crushed 5-1 by the American world No 2 the previous year. This time Kasparov was ready, had a clear edge at move 34, and planned a queen trade by Qe4-c2. A mouse slip landed the queen on d3, and the computer interpreted his attempted correction as a pre-move which blundered a bishop.", "The Guardian",
                new Date(),
                List.of("Chess", "news", "Garry Kasparov")));
        newsList.add(new NewsModel("Surging Miami Heat halfway to NBA finals after Game 2 rally past Celtics",
                "Down by 14 in Game 1, the Miami Heat found a way.\n" +
                        "\n" +
                        "Down by 17 in Game 2, they did it again Thursday night. And after making the Boston Celtics lose another big lead on the court – as well as their cool in the postgame locker room – the unheralded Heat are two wins away from the NBA finals.",
                "The Guardian",
                new Date(),
                List.of("US Sports", "Basketball", "match reports", "Miami Heat")));
        newsList.add(new NewsModel("'Cricket has won': Chris Silverwood upbeat despite England's ODI defeat",
                "So the World Cup winners were humbled with two balls to spare and they lost a home series of one-day internationals for the first time since 2015. However, you would have to be a Victor of Meldrew proportions to rail at that after a remarkable and, hopefully, unique summer of cricket.\n" +
                        "\n" +
                        "England’s head coach, Chris Silverwood, is one of the more cheerful Yorkshiremen on the planet and he was not minded to spend too long agonising over the defeat. “It’s always disappointing to lose,” he said. “But it’s been hard fought, two good teams going at each other and it’s provided some really entertaining cricket.",
                "The Guardian",
                new Date(),
                List.of("news", "Cricket")));
        newsList.add(new NewsModel("Talking Horses: frustration after false Covid link with Doncaster trial ",
                "Armstrong was one of many who felt Wednesday’s statement by Vaughan Gething, the Welsh health minister, risked putting racing back in the Covid-19 pillory, where it had been in the weeks after the Cheltenham Festival in March. “It was just frustration that a headline gets grabbed on the back of other headlines about Cheltenham and creates this storyline that’s not real. We’ve got enough problems at the moment without having to deal with that as well.”",
                "The Guardian",
                new Date(),
                List.of("features", "Horse racing")));
        newsList.add(new NewsModel("Could sport swing the election? Why Trump wants college football to return",
                "Four years ago, Trump was able to win the electoral college and the White House despite receiving nearly 3m fewer votes by toppling the so-called blue wall of Michigan, Pennsylvania and Wisconsin – none of which had gone Republican in nearly three decades – by a combined total of fewer than 80,000 votes (or 0.06% of 137m votes cast). All three of those crucially important battleground states fall squarely inside the Big Ten’s geographical footprint, as do Minnesota, Iowa and Ohio, which also figure into scenarios that could swing the election.\n" +
                        "\n" +
                        "Trump’s breathless lobbying for Big Ten football on Twitter and at his rallies makes a lot more sense after a quick glance at the most recent public polling, which finds him trailing Biden in Michigan (by 7.5%), Pennsylvania (4.8%) and Wisconsin (6.8%). Desperate for a boost in states he can’t afford to surrender, the old New Jersey Generals owner has dialed up a Hail Mary.",
                "The Guardian",
                new Date(),
                List.of("NCAA", "College sports", "US Sports", "features", "Donald Trump")));
        newsList.add(new NewsModel("Andy Murray backs calls to remove Margaret Court's name from tennis arena ",
                "Andy Murray thinks the Australian Open should consider removing Margaret Court’s name from the arena at Melbourne Park as he says the multiple grand slam champion’s values are at odds with what tennis stands for.\n" +
                        "\n" +
                        "The 78-year-old Court, who holds the all-time record of 24 major singles titles, has been heavily criticised for voicing her religious-based opposition to same-sex marriage and transgender athletes.",
                "The Guardian",
                new Date(),
                List.of("news", "Australia sport", "Andy Murray")));
        newsList.add(new NewsModel("Cricket's glorious condensed summer nears end with a grim coda",
                "When the England and Wales Cricket Board first announced the season would be postponed in April, the only obvious comparison was with the last time that had happened, during the war years. One of the many differences being that back then the worry was whether and when they ought to stop playing, rather than when or whether they would ever be able to start. They finally did this year in July, eight weeks late, against West Indies in Southampton, the first Test in what would turn out to be one of the more remarkable summers of English cricket, as memorable as the one before it and better, surely, than the one it replaced.",
                "Andy Bull",
                new Date(),
                List.of("ECB", "England cricket team", "comment", "West Indies cricket team")));
        newsList.add(new NewsModel("Benches could prove difference in Leinster v Saracens showdown",
                "In recent years, replacements have been given different names to give their role a more positive, inclusive flavour. The England head coach, Eddie Jones, came up with finishers, although it bore comparison with non-starters, while Harlequins plumped for game-changers, which was surely not what they required if they brought players on while leading.\n" +
                        "\n" +
                        "Whatever the appellation, benches often make a difference but Saracens will rely more heavily on their starters than Leinster and Richard Wigglesworth, Jamie George, Mako Vunipola, if he plays, and Vincent Koch will, if the game is close, stay on for longer than they would otherwise while Leinster will have no such inhibition.",
                "The Guardian",
                new Date(),
                List.of("features", "Leinster", "Saracens")));
        newsList.add(new NewsModel("How Lamine Diack's 16-year reign in charge of IAAF led to a jail term",
                "At the time the Diacks were able to keep a lid on their scheme following what Papa Massata euphemistically called “lobbying and explanation work” with IAAF staff. That “work” included paying head of communications, Nick Davies, €25,000 and then €5,000 in cash in envelopes partly to assuage his wife, Jane Boultier-Davies, who worked in the anti-doping department and was raising awkward questions. Davies and his wife were found not guilty of corruption by the IAAF ethics board. Eventually, though, the story would blow spectacularly open – leading to Davies losing his job and Lamine moving into the firing line.\n" +
                        "\n" +
                        "The more French investigators began to dig, the more they realised that Shobukhova was far from a lone case. In fact under a scheme devised by Diack Sr, dubbed “full protection”, 22 other Russian athletes paid between €100,000 and €600,000 in exchange for having their doping bans hushed up so they could compete at the London 2012 Olympics and in Moscow a year later. Diack Sr had known all along.",
                "The Guardian",
                new Date(),
                List.of("Drugs in sport", "features", "Sport politics", "Russia doping scandal")));
        newsList.add(new NewsModel("Anatomy of a masterpiece: Glenn Maxwell innings leaves cricket craving more",
                "It is true that Maxwell is not one of those athletes who look like they were engineered in a lab. Nor are lots of cricketers, but not many cricketers hit more sixes than he does. The few whose records compare, like Andre Russell or Kieron Pollard, are giant slabs of human muscle.\n" +
                        "\n" +
                        "When you see Maxwell at the batting crease though, his ability makes a sort of sense. While almost slender, he is rubbery, loose-jointed. His arms are long, his limbs seem to hang in the breeze rather than tensely await contact. The way he stands speaks of potential energy about to be transformed.",
                "Geoff Lemon",
                new Date(),
                List.of("Cricket", "comment")));
        newsList.add(new NewsModel("Premier League: 10 things to look out for this weekend ",
                "Last season Liverpool won 15 league games by the odd goal, and began their title defence in similar style, beating Leeds 4-3 in an indecently entertaining match. But this habit is not an easy one to sustain, and it is worth nothing that they have lost five of their past 14 games, conceding 19 times in the process. It is true that now is a good time to play Chelsea – Kepa Arrizabalaga is still first choice, Ben Chilwell and Hakim Ziyech are unfit, and Timo Werner and Kai Havertz have not fully settled. But Werner still looked sharp against Brighton, while Havertz can change the course of a game at any moment. If Chelsea somehow refrain from tossing goals in their own net, they will ask some serious questions of a back four that has looked more vulnerable of late. DH",
                "The Guardian",
                new Date(),
                List.of("Chelsea", "Liverpool", "Manchester City", "Arsenal")));
        newsList.add(new NewsModel("Arteta admits he doubted whether Aubameyang would stay at Arsenal",
                "Mikel Arteta admits he initially doubted whether Pierre-Emerick Aubameyang would sign a new contract with Arsenal and that the player also had misgivings before being won round.\n" +
                        "\n" +
                        "Aubameyang’s three-year deal was confirmed on Tuesday and put to bed months of speculation about the club captain’s future. It means Arteta can work in the knowledge he has retained his prize asset, but the situation when he took charge last December was less straightforward.",
                "The Guardian",
                new Date(),
                List.of("Premier League", "news")));
        newsList.add(new NewsModel("With fans restive and a US consortium circling, what next for West Ham? ",
                "The past few weeks have been draining. After a period of deceptive calm, West Ham unleashed a wave of negativity when they sold Grady Diangana to West Brom for £18m. Even Mark Noble, the captain, joined in the criticism. Selling the best young talent was not part of the brochure when West Ham left Upton Park in 2016 and, following defeat by Newcastle on the opening day of the new season, supporters are hoping for a brighter future following the news that an American consortium is proposing a takeover.",
                "The Guardian",
                new Date(),
                List.of("Premier League", "features")));
        newsList.add(new NewsModel("Trump Administration to Ban TikTok and WeChat From U.S. App Stores",
                "WASHINGTON — The Trump administration said Friday it would bar the Chinese-owned mobile apps WeChat and TikTok from U.S. app stores as of Sunday, striking a harsh blow against two popular services used by more than 100 million people in the United States.\n" +
                        "\n" +
                        "The restrictions will ban the transferring of funds or processing of payments through WeChat within the United States as of Sunday. In the case of WeChat, the restrictions will also prevent any company from offering internet hosting, content delivery networks, internet transit or peering services to WeChat, or using the app’s code in other software or services in the United States.",
                "New York Times",
                new Date(),
                List.of("TikTok", "Donald Trump")));
        newsList.add(new NewsModel("'Hail, gallant woman': Amy Dorris praised for coming forward with Trump assault allegation",
                "A prominent American former magazine columnist who accused Donald Trump of raping her in the 1990s has joined a chorus of voices supporting the latest woman to accuse Trump of sexually assaulting her, Amy Dorris.\n" +
                        "\n" +
                        "Dorris, a former model and actor, said in a Guardian interview published on Thursday that Trump had forced his tongue down her throat and groped her at the 1997 US Open.\n" +
                        "\n" +
                        "“I feel sick, violated – it makes me sick,” Dorris told the Guardian.\n" +
                        "\n" +
                        "Dorris became the 26th woman to make accusations of sexual misconduct against Trump, ranging from harassment to sexual assault and rape.",
                "The Guardian",
                new Date(),
                List.of("US politics", "news")));
        newsList.add(new NewsModel("Clues to scale of Xinjiang labour operation emerge as China defends camps",
                "The Chinese Communist party government has defended its system of internment camps for Uighur and other ethnic minorities in Xinjiang, in a white paper that also revealed some details of the breadth of its labour program.\n" +
                        "\n" +
                        "In the document published on Thursday, Beijing called them “vocational training centres” , saying: “Through its proactive labor and employment policies, Xinjiang has continuously improved the people’s material and cultural lives, and guaranteed and developed their human rights in every field.”\n" +
                        "\n" +
                        "Figures included in the report hinted at the scope of the program. It said an average of 1.29 million workers, including 415,400 from southern Xinjiang, had gone through “vocational training” ever year between 2014 and 2019, although it didn’t clarify if – or how many times – people had gone through the camps.",
                "The Guardian",
                new Date(),
                List.of("China", "Asia Pacific", "news")));
        newsList.add(new NewsModel("Greece lashed by rare hurricane-force storm",
                "Greek civil protection authorities have been put on maximum alert after a Mediterranean cyclone made landfall, battering the west of the country and many of its popular Ionian islands.\n" +
                        "\n" +
                        "Meteorologists predicted the storm, a rare weather phenomenon referred to as a “medicane”, would pick up speed as it crossed the sea and moved south.\n" +
                        "\n" +
                        "By mid-afternoon on Friday, emergency services were reporting countless calls from citizens trapped in cars along a stretch of highway in central Greece. A boat carrying dozens of migrants was stranded at sea, with coastguard officials saying high winds meant rescue boats were unable to approach the vessel.",
                "The Guardian",
                new Date(),
                List.of("Hurricanes", "news", "Europe")));
        newsList.add(new NewsModel("Dozens jump from migrant rescue ship in attempt to reach Sicily ",
                "Dozens of people have jumped from an NGO migrant rescue ship in an attempt to reach Sicily, after the crew waited 10 days for authorisation to disembark their passengers.\n" +
                        "\n" +
                        "The Spanish NGO Open Arms said 124 of the 273 refugees and migrants on its Proactiva Open Arms boat, leaped into the water during the largest-known incident of its kind.\n" +
                        "\n" +
                        "They were rescued by the Italian coastguard and brought to safety in the port of Palermo.",
                "The Guardian",
                new Date(),
                List.of("Refugees", "Migration", "Europe", "news")));
        newsList.add(new NewsModel("Men allegedly involved in toppling of Colston statue offered cautions",
                "Five men allegedly involved in the toppling of the statue of the slave trader Edward Colston in Bristol have been offered cautions by the police on the condition they explain the reasons for their actions to a history commission.\n" +
                        "\n" +
                        "The men would also have to pay a fine that would go to a charity supporting people from black, Asian and minority ethnic (BAME) communities in Bristol.\n" +
                        "\n" +
                        "Detectives will send their file of evidence on three other men and one woman to the Crown Prosecution Service (CPS) for it to decide whether they should be charged over the incident.",
                "The Guardian",
                new Date(),
                List.of("Bristol", "Black Lives Matter movement", "news")));
        newsList.add(new NewsModel("Right now, children need a champion. But they are losing one",
                "Young people have been hardest hit by austerity, and infant mortality is rising. The new children’s commissioner must not be another Tory crony. oung people have been hardest hit by austerity, and infant mortality is rising. The new children’s commissioner must not be another Tory crony",
                "Mac Apple",
                new Date(),
                List.of("Social care", "Child protection", "Poverty", "Health", "Social exclusion", "comment")));
        newsList.add(new NewsModel("Is Trump, finally, managing to repel even his own supporters? ",
                "That was it, the sum total of his moment of reason. It didn’t last, obviously. Within the space of the same answer, Trump promised that a vaccine would be available within “three weeks, four weeks”, and flogged the same, implausible line that the US response to the pandemic has been one of the best in the world. By Wednesday, the head of the Centers for Disease Control and Prevention had felt compelled to step forward and clarify that, in all likelihood, it would be well into 2021 before a vaccine became available, while proffering the opinion that wearing a mask was as, if not, more important. “I think he made a mistake when he said that,” retorted Trump. “It’s just incorrect information.”",
                "Emma Brockes",
                new Date(),
                List.of("Wildfires", "US elections 2020", "comment", "Coronavirus outbreak")));
        newsList.add(new NewsModel("Hitachi failed its nuclear test. If only it had the vanity of HS2 ",
                "If I were starting a business school I would offer an honours course in vanity infrastructure. In April, Boris Johnson finally issued “notice to proceed” on the most lavish construction project in Europe, Britain’s new railway, HS2. Its value for money was plummeting even before coronavirus, at just £1.20 for every £1 in cost, and possibly heading towards 70p.\n" +
                        "\n" +
                        "Inquiries by the National Audit Office and Commons Accounts Committee were scathing not just at costs soaring from £34bn in 2010 to £106bn today, but at the morass of consultants, facilitators, conflicts of interests and dubious bonuses swilling round HS2 Ltd, its boss pocketing £660,000 a year. Supporters continued to weave and dodge between arguing the case for speedier journeys, more commuter capacity and a “boost for the north”.\n" +
                        "\n" +
                        "What has been intriguing about HS2, like Hinkley Point, is its political invulnerability. From now on it will be charging British taxpayers over £100m a week for the scheduled 20 years of the project. The sums are so stupefying as to have an inverse effect. They are taken as a sign of political machismo, of “build, build, build”. Opponents have included even Johnson and his svengali, Dominic Cummings. Other ministers are only too aware that £100m a week cannot avoid impacting on their projects.",
                "Simon Jenkins",
                new Date(),
                List.of("Rail transport", "Transport", "Boris Johnson", "comment")));
        newsList.add(new NewsModel("How many Trump accusers does it take for his supporters to care? ",
                "It’s wholly unsurprising but entirely disgusting: the president of the United States yet again faces accusations of sexual assault.\n" +
                        "\n" +
                        "This time they come from the former model Amy Dorris, who says Donald Trump groped her and shoved his tongue down her throat at the US Open tennis tournament in 1997. She told her mother and a friend, and later a therapist, all of whom confirmed her story.",
                "Jill filipovic",
                new Date(),
                List.of("comment")));
        newsList.add(new NewsModel("‘Everyone was drenched in the virus’: was this Austrian ski resort a Covid-19 ground zero? ",
                "Ischgl, one of the most popular ski resorts in Europe, is what Jackson calls “a boyish kind of place”. He and his friends had been visiting the town in the Paznaun valley, Austria, for the past nine years. The snow is reliably powdery from November to May. The compact nature of the place means you don’t need a car to get around. The facilities are well-run: Ischgl has 45 state-of-the-art ski lifts, three of which take you directly from the edge of town to the mountain.\n" +
                        "\n" +
                        "Then there are the many apres-ski bars, where Jackson and other tourists party after a hard day on the slopes. “They are a bit like discos for teenagers, but full of men in their 50s,” he says.",
                "Philip Oltermann and Lois Hoyal",
                new Date(),
                List.of("Pubs", "Alcohol", "Ski resorts", "Health & Wellbeing")));
        newsList.add(new NewsModel("Navalny poisoning forces Merkel’s party to ask: how to hit back at Putin?",
                "The assertive manner in which Angela Merkel announced on Wednesday that Navalny, who is lying in an induced coma in a Berlin hospital, had been targeted with a nerve agent from the novichok group raised eyebrows, and not just in the German capital.\n" +
                        "\n" +
                        "Stepping out in front of the press in person, the chancellor described the “attempted murder by poisoning” as “an attack on the fundamental values and basic rights to which we are committed”.",
                "The Guardian",
                new Date(),
                List.of("Russia", "Angela Merkel", "Europe", "features")));
        newsList.add(new NewsModel("Texas is a 'voter suppression' state and one of the hardest places to vote. Will it help Trump win?",
                "Riley, who has a chronic breathing problem, abandoned her post after maybe 30 minutes at the polls, though she didn’t do so thoughtlessly. She has worked elections since 2016, and she understands the difference the staff makes. “I just feel like it matters a lot who’s there,” she said. Things can happen, she said, if there aren’t clerks onsite who “are willing to open their mouth”.",
                "The Guardian",
                new Date(),
                List.of("Donald Trump", "Joe Biden", "US politics", "Republicans")));
        newsList.add(new NewsModel("'Times have changed': Barbadians in Reading welcome republic plans ",
                "Fifity-four years after independence, Barbados stands ready to cast off the final vestige of its colonial past having learned much from its British overlords, Small believes. “The time is right. And the people are ready,” added the grandfather, 75, who lives at the heart of a close community of Barbadians in Reading, home to one of the largest diasporas outside of Barbados.\n" +
                        "\n" +
                        "Today most, like Small, are excited by the prospect for the island, colloquially known as “Little England” since the first English settlers arrived in 1625 to profit from sugarcane plantations and slaves.",
                "The Guardian",
                new Date(),
                List.of("The Queen", "Monarchy", "Americas", "features")));
    }
}