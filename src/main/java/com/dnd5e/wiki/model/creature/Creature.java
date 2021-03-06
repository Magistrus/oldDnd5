package com.dnd5e.wiki.model.creature;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dnd5e.wiki.model.Book;

import lombok.Getter;
import lombok.Setter;

/**
 * Существо
 */
@Entity
@Table(name = "creatures")
@Getter
@Setter
public class Creature {
	private static final String darkvisionTitle = "Чудовище с тёмным зрением может видеть в темноте в определённом радиусе. Чудовище может видеть при тусклом освещении в этом радиусе, как если бы это было нормальное освещение, и в темноте, как при тусклом освещении. Чудовище не может различать цвета в темноте, только оттенки серого. Многие существа, живущие под землёй, обладают этим чувством.";
	private static final String vibrationTitle = "Чудовища с чувством вибрации могут обнаруживать и определять источник колебаний в пределах определённого радиуса при условии, что чудовище и источник колебаний находятся в контакте с одной и той же поверхностью или веществом. Чувство вибрации не может быть использовано для обнаружения летающих или бесплотных существ. Многие роющие существа, такие как анхеги или бурые увальни, обладают этим чувством.";
	private static final String blindsightTitle = "Чудовище со слепым зрением воспринимает окружение в определённом радиусе, не полагаясь на зрение. Как правило, это особое чувство есть у существ без глаз, таких как гримлоки и серая слизь, а также у существ с эхолокацией или обострённым восприятием, таких как летучие мыши и истинные драконы. Если чудовище слепо от природы, это указывается в скобках наряду с радиусом, за пределами которого существо слепо.";
	private static final String truesightTitle = "Чудовище с истинным зрением в определённом радиусе видит в обычной и магической тьме, видит невидимых существ, автоматически распознаёт визуальные иллюзии и успешно преуспевает в спасбросках от них, и видит истинную форму перевёртышей и существ, превращённых магией. Кроме того, зрение этого чудовища простирается на Эфирный План в том же радиусе.";

	private static final String popover = "tabindex=\"0\" href=\"#\" class=\"text-danger\" data-bs-trigger=\"hover\" data-bs-container=\"body\" data-bs-toggle=\"popover\" data-bs-placement=\"top\"";

	private static final String flyTittle = "Существо, имеющее скорость полёта, может использовать часть или всё передвижение для полёта.";
	private static final String swimTittle = "Существо, имеющее скорость плавания, не тратит дополнительное движение при плавании.";
	private static final String climbingTittle = "Существо, имеющее скорость лазания, может использовать часть или всё передвижение для перемещения по вертикальным поверхностям. Чудовищу нет необходимости тратить дополнительное движение для лазания.";
	private static final String diggingTittle = "Существо, имеющее скорость копания, может использовать её для передвижения через песок, землю, грязь или лёд. Чудовище не может копать сквозь камень, если у него нет особого умения, позволяющего делать это.";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String name;
	private String altName;
	private String englishName;

	@Enumerated(EnumType.ORDINAL)
	private CreatureSize size;

	@Enumerated(EnumType.ORDINAL)
	private CreatureType type;

	private Integer raceId;
	private String raceName;

	@Enumerated(EnumType.ORDINAL)
	private Alignment alignment;

	@Column(nullable = false)
	private byte AC;
	private String bonusAC;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<ArmorType> armorTypes;

	private short averageHp;
	private Short countDiceHp;

	@Enumerated(EnumType.ORDINAL)
	private Dice diceHp;

	@Column(nullable = true)
	private Short bonusHP;

	private String suffixHP;
	
	private byte speed = 30;
	@Column(nullable = true)
	private Short flySpeed;
	@Column(nullable = true)
	private Short hover;
	@Column(nullable = true)
	private Short swimmingSpped;
	@Column(nullable = true)
	private Short climbingSpeed;
	@Column(nullable = true)
	private Short diggingSpeed;
	@Column(nullable = true)
	private String speedText;

	// Абилки
	@Column(nullable = false)
	private byte strength = 10;
	@Column(nullable = false)
	private byte dexterity = 10;
	@Column(nullable = false)
	private byte constitution = 10;
	@Column(nullable = false)
	private byte intellect = 10;
	@Column(nullable = false)
	private byte wizdom = 10;
	@Column(nullable = false)
	private byte charisma = 10;

	@ElementCollection
	@Enumerated(EnumType.ORDINAL)
	private List<Condition> immunityStates;

	@ElementCollection
	@Enumerated(EnumType.ORDINAL)
	private List<DamageType> immunityDamages;

	@ElementCollection
	@Enumerated(EnumType.ORDINAL)
	private List<DamageType> resistanceDamages;

	@ElementCollection
	@Enumerated(EnumType.ORDINAL)
	private List<DamageType> vulnerabilityDamages;

	private Integer darkvision;
	private Integer trysight;
	private Integer vibration;
	private Integer blindsight;
	private Integer blindsightRadius;

	private byte passivePerception;

	// опыт
	private int exp;
	// уровень опасности
	private String challengeRating;

	// спаброски
	@OneToMany(cascade = CascadeType.ALL)
	//@JoinColumn(name = "creature_id")
	private List<SavingThrow> savingThrows;

	// навыки
	@OneToMany(cascade = CascadeType.ALL)
	//@JoinColumn(name = "creature_id")
	private List<Skill> skills;

	@ManyToMany
	private List<Language> languages;

	@OneToMany(cascade = CascadeType.ALL)
	private List<CreatureFeat> feats;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Action> actions;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "creature_id")
	private List<CreatureSpell> spells;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(columnDefinition = "TEXT")
	private String legendary;

	@ManyToMany
	private List<CreatureRace> races;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "creature_id")
	private List<Spellcater> spellcasters;
	
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<HabitatType> habitates;

	@OneToOne
	@JoinColumn(name = "lair_id")
	private Lair lair;

	@ManyToOne
	@JoinColumn(name = "source")
	private Book book;
	private Short page;
	
	private String img;

	public String getSizeName() {
		return size.getSizeName(type);
	}

	public String strengthText() {
		return getFormatAbility(strength);
	}

	public String dexterityText() {
		return getFormatAbility(dexterity);
	}

	public String constitutionText() {
		return getFormatAbility(constitution);
	}

	public String intellectText() {
		return getFormatAbility(intellect);
	}

	public String wizdomText() {
		return getFormatAbility(wizdom);
	}

	public String charismaText() {
		return getFormatAbility(charisma);
	}

	private String getFormatAbility(byte ability) {
		return String.format("%d (%+d)", ability, (ability - 10) < 0 ? (ability - 11) / 2 : (ability - 10) / 2);
	}

	public String getArmorTypeString() {
		return armorTypes.stream().map(ArmorType::getCyrillicName).collect(Collectors.joining(", "));
	}

	public String getHp() {
		
		if (bonusHP == null && diceHp == null && suffixHP == null) {
			return String.format("%d", averageHp);
		}
		if (bonusHP == null && diceHp == null && suffixHP != null) {
			return String.format("%d %s", averageHp, suffixHP);
		}
		if (bonusHP == null) {
			return String.format("%d (%d%s)", averageHp, countDiceHp, diceHp.name());
		}
		return String.format("%d (%d%s %s %d)", averageHp, countDiceHp, diceHp.name(), bonusHP>=0 ? "+" : "-", Math.abs(bonusHP));
	}

	public String getHpFormula() {
		if (bonusHP == null && diceHp == null && suffixHP == null) {
			return String.format("%d", averageHp);
		}
		if (bonusHP == null && diceHp == null && suffixHP != null) {
			return String.format("%d %s", averageHp, suffixHP);
		}
		if (bonusHP == null) {
			return String.format("%d%s", countDiceHp, diceHp.name());
		}
		return String.format("%d%s%s%d", countDiceHp, diceHp.name(),  bonusHP>=0 ? "+" : "-", Math.abs(bonusHP));
	}
	
	public String getSense() {
		List<String> sense = new ArrayList<String>(5);
		if (blindsight != null) {
			String blind = String.format("слепое зрение %d фт.", blindsight);
			if (blindsightRadius != null) {
				blind += " (слеп за пределами этого радиуса)";
			}
			sense.add(blind);
		}
		if (darkvision != null) {
			sense.add(String.format("тёмное зрение %d фт.", darkvision));
		}
		if (trysight != null) {
			sense.add(String.format("истинное зрение %d фт.", trysight));
		}
		if (vibration != null) {
			sense.add(String.format("чувство вибрации %d фт.", vibration));
		}
		return sense.stream().collect(Collectors.joining(", "));
	}

	public String getAllSense() {
		List<String> sense = new ArrayList<>(5);
		if (blindsight != null) {
			String blind = String.format("<a class=\"text-danger\" " + popover + " data-bs-content =\"%s\">слепое зрение</a> %d фт.", blindsightTitle, blindsight);
			if (blindsightRadius != null) {
				blind += " (слеп за пределами этого радиуса)";
			}
			sense.add(blind);
		}
		if (darkvision != null) {
			sense.add(String.format("<a class=\"text-danger\" " + popover + " data-bs-content =\"%s\">тёмное зрение</a> %d фт.", darkvisionTitle,
					darkvision));
		}
		if (trysight != null) {
			
			sense.add(String.format("<a class=\"text-danger\" " + popover + " data-bs-content =\"%s\">истинное зрение</a> %d фт.", truesightTitle, trysight));
		}
		if (vibration != null) {
			sense.add(String.format("<a class=\"text-danger\" " + popover + " data-bs-content =\"%s\">чувство вибрации</a> %d фт.", vibrationTitle, vibration));
		}
		sense.add(String.format("пассивная Внимательность %d", passivePerception));

		return sense.stream().collect(Collectors.joining(", "));
	}

	public String getAllSpeedEnglish() {
		return String.format("%d ft.", speed) + (flySpeed == null ? "" : String.format(", fly %d ft.", flySpeed))
				+ (swimmingSpped == null ? "" : String.format(", swim %d ft.", swimmingSpped))
				+ (diggingSpeed == null ? "" : String.format(", burrow %d ft.", diggingSpeed))
				+ (climbingSpeed == null ? "" : String.format(", climb %d ft.", climbingSpeed));
	}

	public String getAllSpeed() {
		return String.format("%d фт.", speed) + (flySpeed == null ? "" : String.format(", <a class=\"text-danger\" "  +popover+ " data-bs-content =\"%s\">летая</a> %d фт.", flyTittle, flySpeed)) 
				+ (hover == null ? "" : " (парит)")
				+ (swimmingSpped == null ? "" : String.format(", <a class=\"text-danger\" " + popover + " data-bs-content =\"%s\">плавая</a> %d фт.",swimTittle, swimmingSpped))
				+ (diggingSpeed == null ? "" : String.format(", <a class=\"text-danger\" " + popover + " data-bs-content =\"%s\">копая</a> %d фт.", diggingTittle, diggingSpeed))
				+ (climbingSpeed == null ? "" : String.format(", <a class=\"text-danger\" " + popover + " data-bs-content =\"%s\">лазая</a> %d фт.", climbingTittle, climbingSpeed))
				+ (speedText == null ? "" : speedText);
	}
	
	public List<Action> getActions(){
		return actions.stream().filter(a -> a.getActionType() == ActionType.ACTION).collect(Collectors.toList());
	}
	
	public List<Action> getReactions(){
		return actions.stream().filter(a -> a.getActionType() == ActionType.REACTION).collect(Collectors.toList());
	}
	
	public List<Action> getBonusActions(){
		return actions.stream().filter(a -> a.getActionType() == ActionType.BONUS).collect(Collectors.toList());
	}

	public List<Action> getLegendaries(){
		return actions.stream().filter(a -> a.getActionType() == ActionType.LEGENDARY).collect(Collectors.toList());
	}
}